package id.heycoding.splashscreen.data.network.model.response

import com.google.gson.annotations.SerializedName
import id.heycoding.shared.data.model.response.UserResponse


/**
 * Created by Irfan Nawawi on 09/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
data class SyncResponse(
    @SerializedName("user")
    val userResponse: UserResponse?
)
