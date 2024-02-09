package id.heycoding.home.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.RecycledViewPool
import id.heycoding.home.presentation.adapter.viewholder.HomeUpcomingViewHolder
import id.heycoding.home.data.network.model.viewparam.homeitem.HomeUiItem
import id.heycoding.home.presentation.adapter.viewholder.HomePopularViewHolder
import id.heycoding.shared.data.model.viewparam.MovieViewParam
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
        return HomeUpcomingViewHolder(
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

interface UpcomingAdapterClickListener {
    fun onUpcomingMovieClicked(upcomingViewParam: MovieViewParam)
}