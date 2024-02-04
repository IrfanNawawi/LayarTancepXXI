package id.heycoding.home.presentation.viewparam

import com.google.gson.annotations.SerializedName
import id.heycoding.shared.data.model.viewparam.MovieViewParam


/**
 * Created by Irfan Nawawi on 17/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
data class SectionViewParam(
    @SerializedName("section_id")
    val sectionId: Int,
    @SerializedName("section_name")
    val sectionName: String,
    @SerializedName("contents")
    val contents: List<MovieViewParam>
)
