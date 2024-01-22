package id.heycoding.home.presentation.adapter.viewholder

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.RecycledViewPool
import id.heycoding.home.databinding.ItemSectionMovieBinding
import id.heycoding.home.presentation.adapter.HomeAdapterClickListener
import id.heycoding.home.presentation.adapter.HomeFeedsAdapter
import id.heycoding.home.presentation.adapter.MovieAdapter
import id.heycoding.home.presentation.viewparam.homeitem.HomeUiItem
import id.heycoding.shared.data.model.viewparam.MovieViewParam


/**
 * Created by Irfan Nawawi on 18/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
class HomeSectionViewHolder(
    private val binding: ItemSectionMovieBinding,
    private val recyclerViewPool: RecycledViewPool,
    private val listener: HomeAdapterClickListener
) : RecyclerView.ViewHolder(binding.root) {

    private val movieAdapter: MovieAdapter by lazy {
        MovieAdapter {
            listener.onMovieClicked(it)
        }
    }

    fun bindView(item: HomeUiItem.ContentSectionItem) {
        with(item) {
            binding.tvTitleSection.text = this.sectionViewParam.sectionName
            movieAdapter.setItems(this.sectionViewParam.contents)
            binding.rvContents.apply {
                setRecycledViewPool(recyclerViewPool)
                adapter = movieAdapter
                layoutManager =
                    LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            }
        }
    }
}