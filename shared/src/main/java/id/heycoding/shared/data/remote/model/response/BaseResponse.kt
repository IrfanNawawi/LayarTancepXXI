package id.heycoding.shared.data.remote.model.response

import com.google.gson.annotations.SerializedName


/**
 * Created by Irfan Nawawi on 05/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
data class BaseResponse<D>(
    @SerializedName("results")
    val data: D?,
    @SerializedName("total_results")
    val totalResults: Int?
)