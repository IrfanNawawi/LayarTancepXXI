package id.heycoding.core.exception


/**
 * Created by Irfan Nawawi on 11/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
class FieldErrorException(val errorFields: List<Pair<Int, Int>>) : Exception()