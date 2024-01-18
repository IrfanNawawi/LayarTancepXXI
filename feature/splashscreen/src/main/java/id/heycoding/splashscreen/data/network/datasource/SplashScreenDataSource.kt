package id.heycoding.splashscreen.data.network.datasource

import id.heycoding.shared.data.model.response.BaseResponse
import id.heycoding.splashscreen.data.network.model.response.SyncResponse
import id.heycoding.splashscreen.data.network.service.SplashScreenFeatureApi


/**
 * Created by Irfan Nawawi on 09/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
interface SplashScreenDataSource {
    suspend fun doUserSync(): BaseResponse<SyncResponse>
}

class SplashScreenDataSourceImpl(val service: SplashScreenFeatureApi) : SplashScreenDataSource {
    override suspend fun doUserSync(): BaseResponse<SyncResponse> {
        return service.doUserSync()
    }

}