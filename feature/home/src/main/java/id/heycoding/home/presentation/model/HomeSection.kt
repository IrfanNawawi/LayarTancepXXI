package id.heycoding.home.presentation.model

import id.heycoding.home.data.network.model.viewparam.homeitem.HomeUiItem


/**
 * Created by Irfan Nawawi on 08/02/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
data class HomeSection (
    val title: String,
    val homeModel: List<HomeUiItem>
)
