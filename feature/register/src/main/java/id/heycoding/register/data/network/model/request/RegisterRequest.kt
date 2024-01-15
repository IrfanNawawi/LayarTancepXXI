package id.heycoding.register.data.network.model.request

import com.google.gson.annotations.SerializedName


/**
 * Created by Irfan Nawawi on 12/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
data class RegisterRequest(
    @SerializedName("birthdate")
    val birthdate: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("gender")
    val gender: String?,
    @SerializedName("password")
    val password: String?,
    @SerializedName("username")
    val username: String?
)
