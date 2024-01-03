package id.heycoding.core.wrapper


/**
 * Created by Irfan Nawawi on 03/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
sealed class DataResource<T>(
    val payload: T? = null,
    val message: String? = null,
    val exception: Exception? = null,
) {
    class Success<T>(data: T) : DataResource<T>(data)
    class Error<T>(exception: Exception?, data: T? = null) : DataResource<T>(data, exception = exception)
}