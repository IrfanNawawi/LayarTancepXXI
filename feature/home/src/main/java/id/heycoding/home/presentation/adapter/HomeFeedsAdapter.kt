package id.heycoding.home.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.RecycledViewPool
import id.heycoding.home.R
import id.heycoding.home.databinding.ItemHeaderHomeBinding
import id.heycoding.home.databinding.ItemSectionMovieBinding
import id.heycoding.home.presentation.adapter.viewholder.HomeHeaderViewHolder
import id.heycoding.home.presentation.adapter.viewholder.HomeSectionViewHolder
import id.heycoding.home.presentation.viewparam.homeitem.HomeUiItem
import id.heycoding.shared.data.model.viewparam.MovieViewParam


/**
 * Created by Irfan Nawawi on 18/01/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
class HomeFeedsAdapter(
    private val listener: HomeAdapterClickListener,
    private val recycledViewPool: RecycledViewPool
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val items = mutableListOf<HomeUiItem>()
    fun setItems(items: List<HomeUiItem>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_header_home -> {
                val binding = ItemHeaderHomeBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                HomeHeaderViewHolder(binding, listener)
            }

            else -> {
                val binding = ItemSectionMovieBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                HomeSectionViewHolder(binding, recycledViewPool, listener)
            }
        }
    }

    override fun getItemCount(): Int = items.size
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HomeHeaderViewHolder -> {
                holder.bindView(items[position] as HomeUiItem.HeaderSectionItem)
            }

            is HomeSectionViewHolder -> {
                holder.bindView(items[position] as HomeUiItem.ContentSectionItem)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is HomeUiItem.HeaderSectionItem -> R.layout.item_header_home
            is HomeUiItem.ContentSectionItem -> R.layout.item_section_movie
        }
    }

}

interface HomeAdapterClickListener {
    fun onMyListClicked(movieViewParam: MovieViewParam)
    fun onPlayMovieClicked(movieViewParam: MovieViewParam)
    fun onMovieClicked(movieViewParam: MovieViewParam)
}