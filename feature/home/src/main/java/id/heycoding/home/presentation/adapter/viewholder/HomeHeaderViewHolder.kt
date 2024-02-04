package id.heycoding.home.presentation.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import coil.load
import id.heycoding.home.databinding.ItemHeaderHomeBinding
import id.heycoding.home.presentation.adapter.HomeAdapterClickListener
import id.heycoding.home.presentation.viewparam.homeitem.HomeUiItem
import id.heycoding.shared.utils.CommonUtils


/**
 * Created by Irfan Nawawi on 18/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
class HomeHeaderViewHolder(
    private val binding: ItemHeaderHomeBinding,
    private val listener: HomeAdapterClickListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bindView(item: HomeUiItem.HeaderSectionItem) {
        with(item.movieViewParam) {
            binding.tvAddToWatchlistHeader.setCompoundDrawablesWithIntrinsicBounds(
                0,
                CommonUtils.getWatchListIcon(isUserWatchlist),
                0,
                0
            )
            binding.ivHeaderMovie.load(this.posterUrl)
            binding.tvTitleMovie.text = this.title
            binding.tvInfoHeader.setOnClickListener {
                listener.onMovieClicked(this)
            }
            binding.tvAddToWatchlistHeader.setOnClickListener {
                listener.onMyListClicked(this)
            }
            binding.cvPlayHeader.setOnClickListener {
                listener.onPlayMovieClicked(this)
            }
        }
    }
}