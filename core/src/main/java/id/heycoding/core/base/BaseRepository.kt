package id.heycoding.core.base

import id.heycoding.core.exception.ApiErrorException
import id.heycoding.core.exception.NoInternetConnectionException
import id.heycoding.core.exception.UnexpectedErrorException
import id.heycoding.core.wrapper.DataResource
import retrofit2.HttpException
import java.io.IOException


/**
 * Created by Irfan Nawawi on 03/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
abstract class BaseRepository {
    abstract fun <T> getErrorMessageFromApi(response: T): String

    suspend fun <T> safeNetworkCall(apiCall: suspend () -> T): DataResource<T> {
        return try {
            DataResource.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> DataResource.Error(NoInternetConnectionException())
                is HttpException -> {
                    DataResource.Error(
                        ApiErrorException(
                            getErrorMessageFromApi(
                                throwable.response()?.errorBody()
                            ), throwable.code()
                        )
                    )
                }

                else -> {
                    DataResource.Error(UnexpectedErrorException())
                }
            }
        }
    }

    suspend fun <T> proceed(coroutine: suspend () -> T): DataResource<T> {
        return try {
            DataResource.Success(coroutine.invoke())
        } catch (exception: Exception) {
            DataResource.Error(exception)
        }
    }
}