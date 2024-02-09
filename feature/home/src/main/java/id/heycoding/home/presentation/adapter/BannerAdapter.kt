package id.heycoding.home.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.heycoding.home.data.network.model.viewparam.homeitem.HomeUiItem
import id.heycoding.home.databinding.ItemHeaderHomeBinding
import id.heycoding.home.presentation.adapter.viewholder.HomeBannerViewHolder


/**
 * Created by Irfan Nawawi on 07/02/24.
 * heycoding.tech
 * heycoding@gmail.com
 */
class BannerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val items = mutableListOf<HomeUiItem>()
    fun setItems(items: List<HomeUiItem>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HomeBannerViewHolder(
            ItemHeaderHomeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as HomeBannerViewHolder).bindView(items[position] as HomeUiItem.PopularSectionItem)
    }
}