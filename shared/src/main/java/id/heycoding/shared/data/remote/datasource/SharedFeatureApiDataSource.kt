package id.heycoding.shared.data.remote.datasource

import id.heycoding.shared.data.model.request.WatchlistRequest
import id.heycoding.shared.data.model.response.BaseResponse
import id.heycoding.shared.data.remote.services.SharedFeatureApi


/**
 * Created by Irfan Nawawi on 06/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
interface SharedFeatureApiDataSource {
    suspend fun addWatchlist(request: WatchlistRequest): BaseResponse<Any>
    suspend fun removeWatchlist(request: WatchlistRequest): BaseResponse<Any>
}

class SharedFeatureApiDataSourceImpl(private val sharedFeatureApi: SharedFeatureApi) :
    SharedFeatureApiDataSource {
    override suspend fun addWatchlist(request: WatchlistRequest): BaseResponse<Any> {
        return sharedFeatureApi.addWatchlist(request)
    }

    override suspend fun removeWatchlist(request: WatchlistRequest): BaseResponse<Any> {
        return sharedFeatureApi.removeWatchlist(request)
    }

}