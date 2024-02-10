package id.heycoding.home.presentation.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import coil.load
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import id.heycoding.core.BuildConfig
import id.heycoding.home.data.network.model.viewparam.homeitem.HomeUiItem
import id.heycoding.home.databinding.ItemBannerHomeBinding
import id.heycoding.home.databinding.ItemPopularMovieBinding
import id.heycoding.home.databinding.ItemUpcomingMovieBinding
import id.heycoding.home.presentation.adapter.HomeAdapterClickListener
import id.heycoding.home.presentation.adapter.MovieBannerAdapter
import id.heycoding.home.presentation.adapter.MoviePopularAdapter
import id.heycoding.home.presentation.adapter.MovieUpcomingAdapter


/**
 * Created by Irfan Nawawi on 10/02/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
sealed class HomeViewHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {

    class HomeBannerViewHolder(
        private val binding: ItemBannerHomeBinding,
        private val recyclerViewPool: RecyclerView.RecycledViewPool,
        private val listener: HomeAdapterClickListener
    ) : HomeViewHolder(binding) {

        private val movieBannerAdapter: MovieBannerAdapter by lazy {
            MovieBannerAdapter {
                listener.onMovieClicked(it)
            }
        }

        fun bind(item: HomeUiItem.BannerSectionItem) {
            with(item) {
                movieBannerAdapter.setItems(listOf(this.bannerViewParam))
                binding.rvMovieBanner.apply {
                    setRecycledViewPool(recyclerViewPool)
                    adapter = movieBannerAdapter
                    layoutManager =
                        LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
                }
            }
        }

    }

    class HomePopularViewHolder(
        private val binding: ItemPopularMovieBinding,
        private val recyclerViewPool: RecyclerView.RecycledViewPool,
        private val listener: HomeAdapterClickListener
    ) : HomeViewHolder(binding) {

        private val moviePopularAdapter: MoviePopularAdapter by lazy {
            MoviePopularAdapter {
                listener.onMovieClicked(it)
            }
        }

        fun bind(item: HomeUiItem.PopularSectionItem) {
            with(item) {
                binding.tvTitlePopular.text = this.title
                moviePopularAdapter.setItems(listOf(this.popularViewParam))
                binding.rvMoviePopular.apply {
                    setRecycledViewPool(recyclerViewPool)
                    adapter = moviePopularAdapter
                    layoutManager =
                        LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
                }
            }
        }
    }

    class HomeUpcomingViewHolder(
        private val binding: ItemUpcomingMovieBinding,
        private val recyclerViewPool: RecyclerView.RecycledViewPool,
        private val listener: HomeAdapterClickListener
    ) : HomeViewHolder(binding) {

        private val movieUpcomingAdapter: MovieUpcomingAdapter by lazy {
            MovieUpcomingAdapter {
                listener.onMovieClicked(it)
            }
        }

        fun bind(item: HomeUiItem.UpcomingSectionItem) {
            with(item) {
                binding.tvTitleUpcoming.text = this.title
                movieUpcomingAdapter.setItems(listOf(this.upcomingViewParam))
                binding.rvMovieUpcoming.apply {
                    setRecycledViewPool(recyclerViewPool)
                    adapter = movieUpcomingAdapter
                    layoutManager =
                        LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
                }
            }
        }


    }

}