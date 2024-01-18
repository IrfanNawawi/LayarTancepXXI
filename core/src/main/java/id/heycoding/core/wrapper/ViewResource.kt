package id.heycoding.core.wrapper


/**
 * Created by Irfan Nawawi on 03/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
sealed class ViewResource<T>(
    val payload: T? = null,
    val message: String? = null,
    val exception: Exception? = null,
) {
    class Success<T>(data: T) : ViewResource<T>(data)
    class Error<T>(exception: Exception?, data: T? = null) :
        ViewResource<T>(data, exception = exception)

    class Empty<T>(data: T? = null, msg: String? = null) : ViewResource<T>(data)
    class Loading<T>(data: T? = null) : ViewResource<T>(data)
}