package id.heycoding.shared.data.model.response

import com.google.gson.annotations.SerializedName


/**
 * Created by Irfan Nawawi on 05/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
data class BaseResponse<D>(
    @SerializedName("code")
    val code: Int?,
    @SerializedName("success")
    val success: String?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("data")
    val data: D?,
)