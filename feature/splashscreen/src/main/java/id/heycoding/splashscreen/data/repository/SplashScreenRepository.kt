package id.heycoding.splashscreen.data.repository

import id.heycoding.core.wrapper.DataResource
import id.heycoding.shared.data.model.response.BaseResponse
import id.heycoding.shared.data.repository.Repository
import id.heycoding.splashscreen.data.network.datasource.SplashScreenDataSource
import id.heycoding.splashscreen.data.network.model.response.SyncResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


/**
 * Created by Irfan Nawawi on 09/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */

typealias SyncDataResponse = DataResource<BaseResponse<SyncResponse>>

interface SplashScreenRepository {
    suspend fun doUserSync(): Flow<SyncDataResponse>
}

class SplashScreenRepositoryImpl(val dataSource: SplashScreenDataSource) : SplashScreenRepository,
    Repository() {
    override suspend fun doUserSync(): Flow<SyncDataResponse> {
        return flow {
            emit(safeNetworkCall { dataSource.doUserSync() })
        }
    }

}