package id.heycoding.home.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import id.heycoding.home.R
import id.heycoding.home.data.network.model.viewparam.homeitem.HomeUiItem
import id.heycoding.home.databinding.ItemBannerHomeBinding
import id.heycoding.home.databinding.ItemPopularMovieBinding
import id.heycoding.home.databinding.ItemUpcomingMovieBinding
import id.heycoding.home.presentation.adapter.viewholder.HomeViewHolder
import id.heycoding.shared.data.model.viewparam.MovieViewParam
import java.lang.IllegalArgumentException


/**
 * Created by Irfan Nawawi on 07/02/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
class HomeAdapter(
    private val listener: HomeAdapterClickListener,
    private val recyclerViewPool: RecyclerView.RecycledViewPool
) : ListAdapter<HomeUiItem, HomeViewHolder>(DiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return when (viewType) {
            R.layout.item_banner_home -> HomeViewHolder.HomeBannerViewHolder(
                ItemBannerHomeBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                ), recyclerViewPool, listener
            )

            R.layout.item_popular_movie -> HomeViewHolder.HomePopularViewHolder(
                ItemPopularMovieBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ), recyclerViewPool, listener
            )

            R.layout.item_upcoming_movie -> HomeViewHolder.HomeUpcomingViewHolder(
                ItemUpcomingMovieBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ), recyclerViewPool, listener
            )

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val item = getItem(position)
        when (holder) {
            is HomeViewHolder.HomeBannerViewHolder -> {
                holder.bind(item as HomeUiItem.BannerSectionItem)
            }

            is HomeViewHolder.HomePopularViewHolder -> {
                holder.bind(item as HomeUiItem.PopularSectionItem)
            }

            is HomeViewHolder.HomeUpcomingViewHolder -> {
                holder.bind(item as HomeUiItem.UpcomingSectionItem)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is HomeUiItem.BannerSectionItem -> R.layout.item_banner_home
            is HomeUiItem.PopularSectionItem -> R.layout.item_popular_movie
            is HomeUiItem.UpcomingSectionItem -> R.layout.item_upcoming_movie
        }
    }

    class DiffCallBack : DiffUtil.ItemCallback<HomeUiItem>() {

        override fun areItemsTheSame(oldItem: HomeUiItem, newItem: HomeUiItem): Boolean {
            return when {
                oldItem is HomeUiItem.BannerSectionItem && newItem is HomeUiItem.BannerSectionItem -> {
                    oldItem.bannerViewParam == newItem.bannerViewParam
                }

                oldItem is HomeUiItem.PopularSectionItem && newItem is HomeUiItem.PopularSectionItem -> {
                    oldItem.popularViewParam == newItem.popularViewParam
                }

                oldItem is HomeUiItem.UpcomingSectionItem && newItem is HomeUiItem.UpcomingSectionItem -> {
                    oldItem.upcomingViewParam == newItem.upcomingViewParam
                }

                else -> {
                    false
                }
            }
        }

        override fun areContentsTheSame(oldItem: HomeUiItem, newItem: HomeUiItem): Boolean {
            return when {
                oldItem is HomeUiItem.BannerSectionItem && newItem is HomeUiItem.BannerSectionItem -> {
                    oldItem == newItem
                }

                oldItem is HomeUiItem.PopularSectionItem && newItem is HomeUiItem.PopularSectionItem -> {
                    oldItem == newItem
                }

                oldItem is HomeUiItem.UpcomingSectionItem && newItem is HomeUiItem.UpcomingSectionItem -> {
                    oldItem == newItem
                }

                else -> {
                    false
                }
            }
        }

    }

}


interface HomeAdapterClickListener {
    fun onMyListClicked(movieViewParam: MovieViewParam)
    fun onPlayMovieClicked(movieViewParam: MovieViewParam)
    fun onMovieClicked(movieViewParam: MovieViewParam)
}