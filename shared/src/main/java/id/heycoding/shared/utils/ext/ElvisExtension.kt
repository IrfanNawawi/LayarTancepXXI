package id.heycoding.shared.utils.ext


/**
 * Created by Irfan Nawawi on 26/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
fun Int?.orNol(): Int = this ?: 0
fun Double?.orNolDouble(): Double = this ?: 0.0
fun Float?.orNolFloat(): Float = this ?: 0f
fun Long?.orNolLong(): Long = this ?: 0L
fun Boolean?.orFalse(): Boolean = this ?: false