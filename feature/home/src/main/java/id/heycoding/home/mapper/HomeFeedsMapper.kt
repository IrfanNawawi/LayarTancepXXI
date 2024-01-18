package id.heycoding.home.mapper

import id.heycoding.home.data.network.model.response.HomeFeedsResponse
import id.heycoding.home.data.network.model.response.SectionResponse
import id.heycoding.home.presentation.viewparam.HomeFeedsViewParam
import id.heycoding.home.presentation.viewparam.SectionViewParam
import id.heycoding.shared.data.model.mapper.MovieMapper
import id.heycoding.shared.utils.mapper.ListMapper
import id.heycoding.shared.utils.mapper.ViewParamMapper


/**
 * Created by Irfan Nawawi on 17/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
object HomeFeedsMapper : ViewParamMapper<HomeFeedsResponse, HomeFeedsViewParam> {
    override fun toViewParam(dataObject: HomeFeedsResponse?): HomeFeedsViewParam =
        HomeFeedsViewParam(
            MovieMapper.toViewParam(dataObject?.header),
            ListMapper(SectionMapper).toViewParams(dataObject?.sections)
        )

}

object SectionMapper : ViewParamMapper<SectionResponse, SectionViewParam> {
    override fun toViewParam(dataObject: SectionResponse?): SectionViewParam = SectionViewParam(
        sectionId = dataObject?.sectionId ?: -1,
        sectionName = dataObject?.sectionName.orEmpty(),
        contents = ListMapper(MovieMapper).toViewParams(dataObject?.contents)
    )

}
