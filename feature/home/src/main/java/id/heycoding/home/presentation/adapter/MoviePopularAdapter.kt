package id.heycoding.home.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import id.heycoding.core.BuildConfig
import id.heycoding.shared.data.model.viewparam.MovieViewParam
import id.heycoding.styling.databinding.ItemMoviePosterBinding


class MoviePopularAdapter(
    private val itemClicked: (MovieViewParam) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items = mutableListOf<MovieViewParam>()

    fun setItems(items: List<MovieViewParam>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PopularViewHolderImpl(
            ItemMoviePosterBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            itemClicked
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PopularViewHolder).bindView(items[position])
    }

    override fun getItemCount(): Int = items.size

}

interface PopularViewHolder {
    fun bindView(item: MovieViewParam)
}

class PopularViewHolderImpl(
    private val binding: ItemMoviePosterBinding,
    private val itemClicked: (MovieViewParam) -> Unit
) : RecyclerView.ViewHolder(binding.root), PopularViewHolder {
    override fun bindView(item: MovieViewParam) {
        with(item) {
            val poster: String =
                BuildConfig.BASE_URL_IMAGE + "w500" + this.posterPath
            binding.ivPoster.load(poster)
            itemView.setOnClickListener { itemClicked.invoke(this) }
        }
    }
}