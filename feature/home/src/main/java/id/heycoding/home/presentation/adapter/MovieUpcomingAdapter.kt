package id.heycoding.home.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import id.heycoding.core.BuildConfig
import id.heycoding.shared.data.model.viewparam.MovieViewParam
import id.heycoding.styling.databinding.ItemMoviePosterBinding


class MovieUpcomingAdapter(
    private val itemClicked: (MovieViewParam) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items = mutableListOf<MovieViewParam>()

    fun setItems(items: List<MovieViewParam>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return UpcomingViewHolderImpl(
            ItemMoviePosterBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            itemClicked
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as UpcomingViewHolder).bindView(items[position])
    }

    override fun getItemCount(): Int = items.size

}

interface UpcomingViewHolder {
    fun bindView(item: MovieViewParam)
}

class UpcomingViewHolderImpl(
    private val binding: ItemMoviePosterBinding,
    private val itemClicked: (MovieViewParam) -> Unit
) : RecyclerView.ViewHolder(binding.root), UpcomingViewHolder {
    override fun bindView(item: MovieViewParam) {
        with(item) {
            val poster: String =
                BuildConfig.BASE_URL_IMAGE + "w500" + this.posterPath
            binding.ivPoster.load(poster)
            itemView.setOnClickListener { itemClicked.invoke(this) }
        }
    }
}