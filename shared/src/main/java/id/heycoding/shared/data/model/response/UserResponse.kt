package id.heycoding.shared.data.model.response

import com.google.gson.annotations.SerializedName


/**
 * Created by Irfan Nawawi on 04/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
data class UserResponse(
    @SerializedName("birthdate")
    val birthdate: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("gender")
    val gender: Int?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("username")
    val username: String?
)
