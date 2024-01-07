package id.heycoding.shared.data.model.response

import com.google.gson.annotations.SerializedName


/**
 * Created by Irfan Nawawi on 05/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
data class AuthResponse(
    @SerializedName("token")
    val token: String?,
    @SerializedName("token_type")
    val tokenType: String?,
    @SerializedName("user")
    val user: UserResponse?
)
