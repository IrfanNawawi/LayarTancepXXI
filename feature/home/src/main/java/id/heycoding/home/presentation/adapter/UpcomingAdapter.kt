package id.heycoding.home.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import id.heycoding.core.BuildConfig
import id.heycoding.home.data.remote.model.viewparam.homeitem.HomeUiItem
import id.heycoding.shared.data.remote.model.viewparam.MovieViewParam
import id.heycoding.styling.databinding.ItemMoviePosterBinding

/**
 * Created by Irfan Nawawi on 30/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
class UpcomingAdapter(private val listener: UpcomingAdapterClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val items = mutableListOf<HomeUiItem>()
    fun setItems(items: List<HomeUiItem>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HomeUpcomingViewHolderImpl(
            ItemMoviePosterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), listener
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as HomeUpcomingViewHolder).bindView(items[position] as HomeUiItem.UpcomingSectionItem)
    }
}

interface HomeUpcomingViewHolder {
    fun bindView(item: HomeUiItem.UpcomingSectionItem)
}

class HomeUpcomingViewHolderImpl(
    private val binding: ItemMoviePosterBinding,
    private val listener: UpcomingAdapterClickListener
) : RecyclerView.ViewHolder(binding.root), HomeUpcomingViewHolder {
    override fun bindView(item: HomeUiItem.UpcomingSectionItem) {
        with(item.upcomingViewParam) {
            val poster: String =
                BuildConfig.BASE_URL_IMAGE + "w500" + this.posterPath
            binding.ivPoster.load(poster)
            itemView.setOnClickListener {
                listener.onUpcomingMovieClicked(this)
            }
        }
    }
}

interface UpcomingAdapterClickListener {
    fun onUpcomingMovieClicked(upcomingViewParam: MovieViewParam)
}