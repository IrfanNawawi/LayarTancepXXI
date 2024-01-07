package id.heycoding.shared.data.model.viewparam

import com.google.gson.annotations.SerializedName


/**
 * Created by Irfan Nawawi on 05/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
data class UserViewParam(
    val birthdate: String,
    val email: String,
    val gender: Int,
    val id: Int,
    val username: String,
)
