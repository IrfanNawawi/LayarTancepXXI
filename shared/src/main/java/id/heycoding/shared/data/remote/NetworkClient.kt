package id.heycoding.shared.data.remote

import com.chuckerteam.chucker.api.ChuckerInterceptor
import id.heycoding.core.BuildConfig
import id.heycoding.shared.data.remote.services.interceptor.TmdbAuthInterceptor
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
    val chuckerInterceptor: ChuckerInterceptor,
    val tmdbAuthInterceptor: TmdbAuthInterceptor
) {
    inline fun <reified I> create(): I {
        // okhttp
        val okhhtp = OkHttpClient.Builder()
            .addInterceptor(tmdbAuthInterceptor)
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