package id.heycoding.home.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import id.heycoding.shared.data.model.viewparam.MovieViewParam
import id.heycoding.styling.databinding.ItemMoviePosterBinding
import id.heycoding.styling.databinding.ItemMoviePosterGridBinding


/**
 * Created by Irfan Nawawi on 18/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
class MovieAdapter(
    private val isGridLayout: Boolean = false,
    private val itemClicked: (MovieViewParam) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items = mutableListOf<MovieViewParam>()
    fun setItems(items: List<MovieViewParam>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (!isGridLayout) {
            PosterViewHolderImpl(
                ItemMoviePosterBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                itemClicked
            )
        } else {
            GridViewHolderImpl(
                ItemMoviePosterGridBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ),
                itemClicked
            )
        }
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PosterViewHolder).bindView(items[position])
    }

}

interface PosterViewHolder {
    fun bindView(item: MovieViewParam)
}

class PosterViewHolderImpl(
    private val binding: ItemMoviePosterBinding,
    private val itemClicked: (MovieViewParam) -> Unit
) : RecyclerView.ViewHolder(binding.root), PosterViewHolder {
    override fun bindView(item: MovieViewParam) {
        with(item) {
            binding.ivPoster.load(item.posterUrl)
            itemView.setOnClickListener { itemClicked.invoke(this) }
        }
    }

}

class GridViewHolderImpl(
    private val binding: ItemMoviePosterGridBinding,
    private val itemClicked: (MovieViewParam) -> Unit
) : RecyclerView.ViewHolder(binding.root), PosterViewHolder {
    override fun bindView(item: MovieViewParam) {
        with(item) {
            binding.ivPoster.load(item.posterUrl)
            itemView.setOnClickListener { itemClicked.invoke(this) }
        }
    }

}