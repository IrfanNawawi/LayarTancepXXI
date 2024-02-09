package id.heycoding.home.presentation.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import id.heycoding.core.BuildConfig
import coil.load
import id.heycoding.home.data.network.model.viewparam.homeitem.HomeUiItem
import id.heycoding.home.presentation.adapter.UpcomingAdapterClickListener
import id.heycoding.styling.databinding.ItemMoviePosterBinding


/**
 * Created by Irfan Nawawi on 05/02/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
class HomeUpcomingViewHolder(
    private val binding: ItemMoviePosterBinding,
    private val listener: UpcomingAdapterClickListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bindView(item: HomeUiItem.UpcomingSectionItem) {
        with(item.upcomingViewParam) {
            val poster: String =
                BuildConfig.BASE_URL_IMAGE + "w500" + posterPath
            binding.ivPoster.load(poster)
            itemView.setOnClickListener {
                listener.onUpcomingMovieClicked(this)
            }
        }
    }
}