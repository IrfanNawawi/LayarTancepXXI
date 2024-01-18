package id.heycoding.home.data.network.model.response

import com.google.gson.annotations.SerializedName
import id.heycoding.shared.data.model.response.MovieResponse


/**
 * Created by Irfan Nawawi on 17/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
data class HomeFeedsResponse(
    @SerializedName("header")
    val header: MovieResponse?,
    @SerializedName("sections")
    val sections: List<SectionResponse>?,
)