package id.heycoding.shared.data.remote

import com.chuckerteam.chucker.api.ChuckerInterceptor
import id.heycoding.core.BuildConfig
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * Created by Irfan Nawawi on 04/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
class NetworkClient(
    val chuckerInterceptor: ChuckerInterceptor
) {
    inline fun <reified I> create(): I {
        val authInterceptor = Interceptor {
            val requestBuilder = it.request().newBuilder()
            runBlocking {
                requestBuilder.addHeader("Authorization", "Bearer ${BuildConfig.AUTH_TOKEN}")
            }
            it.proceed(requestBuilder.build())
        }
        // okhttp
        val okhhtp = OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(chuckerInterceptor)
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okhhtp)
            .build()

        return retrofit.create(I::class.java)
    }
}