package id.heycoding.home.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import id.heycoding.core.BuildConfig
import id.heycoding.home.data.remote.model.viewparam.homeitem.HomeUiItem
import id.heycoding.shared.data.remote.model.viewparam.MovieViewParam
import id.heycoding.styling.databinding.ItemMovieHeaderBinding


/**
 * Created by Irfan Nawawi on 07/02/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
class BannerAdapter(private val listener: BannerAdapterClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val items = mutableListOf<HomeUiItem>()
    fun setItems(items: List<HomeUiItem>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HomeBannerViewHolderImpl(
            ItemMovieHeaderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), listener
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as HomeBannerViewHolder).bindView(items[position] as HomeUiItem.PopularSectionItem)
    }
}

interface HomeBannerViewHolder {
    fun bindView(item: HomeUiItem.PopularSectionItem)
}

class HomeBannerViewHolderImpl(
    private val binding: ItemMovieHeaderBinding,
    private val listener: BannerAdapterClickListener
) : RecyclerView.ViewHolder(binding.root), HomeBannerViewHolder {
    override fun bindView(item: HomeUiItem.PopularSectionItem) {
        with(item.popularViewParam) {
            val poster: String =
                BuildConfig.BASE_URL_IMAGE + "w500" + this.posterPath
            binding.ivHeaderMovie.load(poster)
            binding.tvTitleMovie.text = this.title
            binding.cvPlayHeader.setOnClickListener {
                listener.onPlayMovieClicked(this)
            }
            binding.tvAddToWatchlistHeader.setOnClickListener {
                listener.onMyListClicked(this)
            }
            binding.tvInfoHeader.setOnClickListener {
                listener.onBannerMovieClicked(this)
            }
        }
    }
}

interface BannerAdapterClickListener {
    fun onMyListClicked(movieViewParam: MovieViewParam)
    fun onPlayMovieClicked(movieViewParam: MovieViewParam)
    fun onBannerMovieClicked(bannerViewParam: MovieViewParam)
}