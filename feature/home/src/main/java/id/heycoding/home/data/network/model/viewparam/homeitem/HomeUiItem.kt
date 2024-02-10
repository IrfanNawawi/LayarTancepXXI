package id.heycoding.home.data.network.model.viewparam.homeitem

import id.heycoding.shared.data.model.viewparam.MovieViewParam


/**
 * Created by Irfan Nawawi on 17/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
sealed class HomeUiItem {

    data class BannerSectionItem(val bannerViewParam: MovieViewParam) : HomeUiItem()
    data class PopularSectionItem(val title: String, val popularViewParam: MovieViewParam) : HomeUiItem()
    data class UpcomingSectionItem(val title: String, val upcomingViewParam: MovieViewParam) : HomeUiItem()
}