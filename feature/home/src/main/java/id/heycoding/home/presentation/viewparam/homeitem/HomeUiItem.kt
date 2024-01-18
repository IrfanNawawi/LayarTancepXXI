package id.heycoding.home.presentation.viewparam.homeitem

import id.heycoding.home.presentation.viewparam.SectionViewParam
import id.heycoding.shared.data.model.viewparam.MovieViewParam


/**
 * Created by Irfan Nawawi on 17/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
sealed class HomeUiItem {
    class HeaderSectionItem(val movieViewParam: MovieViewParam) : HomeUiItem()
    class ContentSectionItem(val sectionViewParam: SectionViewParam) : HomeUiItem()
}