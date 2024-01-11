package id.heycoding.login.data.network.model.request

import com.google.gson.annotations.SerializedName


/**
 * Created by Irfan Nawawi on 10/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
data class LoginRequest(
    @SerializedName("email")
    val email: String?,
    @SerializedName("password")
    val password: String?
)
