package id.heycoding.home.presentation.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import coil.load
import id.heycoding.core.BuildConfig
import id.heycoding.home.data.network.model.viewparam.homeitem.HomeUiItem
import id.heycoding.home.databinding.ItemHeaderHomeBinding
import id.heycoding.home.presentation.adapter.PopularAdapterClickListener


/**
 * Created by Irfan Nawawi on 07/02/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
class HomeBannerViewHolder(
    private val binding: ItemHeaderHomeBinding
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindView(item: HomeUiItem.PopularSectionItem) {
        with(item.popularViewParam) {
            val poster: String =
                BuildConfig.BASE_URL_IMAGE + "w500" + posterPath
            binding.ivHeaderMovie.load(poster)
        }
    }
}