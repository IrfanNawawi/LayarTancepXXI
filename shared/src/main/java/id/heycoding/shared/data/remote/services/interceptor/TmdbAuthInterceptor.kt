package id.heycoding.shared.data.remote.services.interceptor

import id.heycoding.core.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response


/**
 * Created by Irfan Nawawi on 14/02/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
class TmdbAuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val requestBuilder = originalRequest.newBuilder()
            .header("Authorization", "Bearer ${BuildConfig.AUTH_TOKEN}")
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}
