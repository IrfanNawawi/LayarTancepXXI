package id.heycoding.splashscreen.data.network.service

import id.heycoding.shared.data.model.response.BaseResponse
import id.heycoding.splashscreen.data.network.model.response.SyncResponse
import retrofit2.http.GET


/**
 * Created by Irfan Nawawi on 09/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
interface SplashScreenFeatureApi {
    @GET("/api/v1/sync")
    suspend fun doUserSync(): BaseResponse<SyncResponse>
}