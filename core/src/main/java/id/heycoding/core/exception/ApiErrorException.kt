package id.heycoding.core.exception


/**
 * Created by Irfan Nawawi on 03/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
class ApiErrorException(override val message: String? = null, val httpCode: Int? = null) : Exception()