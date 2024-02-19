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
 * Created by Irfan Nawawi on 28/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
class PopularAdapter(private val listener: PopularAdapterClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val items = mutableListOf<HomeUiItem>()
    fun setItems(items: List<HomeUiItem>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HomePopularViewHolderImp(
            ItemMoviePosterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), listener
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as HomePopularViewHolder).bindView(items[position] as HomeUiItem.PopularSectionItem)
    }
}

interface HomePopularViewHolder {
    fun bindView(item: HomeUiItem.PopularSectionItem)
}

class HomePopularViewHolderImp(
    private val binding: ItemMoviePosterBinding,
    private val listener: PopularAdapterClickListener
) : RecyclerView.ViewHolder(binding.root), HomePopularViewHolder {
    override fun bindView(item: HomeUiItem.PopularSectionItem) {
        with(item.popularViewParam) {
            val poster: String =
                BuildConfig.BASE_URL_IMAGE + "w500" + this.posterPath
            binding.ivPoster.load(poster)
            itemView.setOnClickListener {
                listener.onPopularMovieClicked(this)
            }
        }
    }
}

interface PopularAdapterClickListener {
    fun onPopularMovieClicked(popularViewParam: MovieViewParam)
}