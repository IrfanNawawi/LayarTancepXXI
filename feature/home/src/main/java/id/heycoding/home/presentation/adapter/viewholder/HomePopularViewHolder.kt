package id.heycoding.home.presentation.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import id.heycoding.core.BuildConfig
import coil.load
import id.heycoding.home.presentation.adapter.PopularAdapterClickListener
import id.heycoding.home.data.network.model.viewparam.homeitem.HomeUiItem
import id.heycoding.styling.databinding.ItemMoviePosterBinding


/**
 * Created by Irfan Nawawi on 05/02/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
class HomePopularViewHolder(
    private val binding: ItemMoviePosterBinding,
    private val listener: PopularAdapterClickListener
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindView(item: HomeUiItem.PopularSectionItem) {
        with(item.popularViewParam) {
            val poster: String =
                BuildConfig.BASE_URL_IMAGE + "w500" + posterPath
            binding.ivPoster.load(poster)
            itemView.setOnClickListener {
                listener.onPopularMovieClicked(this)
            }
        }
    }
}