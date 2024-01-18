package id.heycoding.home.presentation.viewparam

import com.google.gson.annotations.SerializedName
import id.heycoding.shared.data.model.response.MovieResponse
import id.heycoding.shared.data.model.viewparam.MovieViewParam


/**
 * Created by Irfan Nawawi on 17/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
data class HomeFeedsViewParam(
    @SerializedName("header")
    val header: MovieViewParam,
    @SerializedName("sections")
    val sections: List<SectionViewParam>,
)