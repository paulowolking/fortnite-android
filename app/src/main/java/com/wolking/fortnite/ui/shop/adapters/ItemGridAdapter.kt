package com.wolking.fortnite.ui.shop.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.wolking.fortnite.R
import com.wolking.fortnite.data.model.Entries
import kotlinx.android.synthetic.main.adapter_grid_item.view.*


class ItemGridAdapter(
    private var context: Context,
    private var shopList: MutableList<Entries> = mutableListOf(),
    private val listener: ItemAdapterListener
) : RecyclerView.Adapter<ItemGridAdapter.ViewHolder>() {

    constructor(
        context: Context, listener: ItemAdapterListener
    ) : this(
        context, mutableListOf<Entries>(), listener = listener
    )

    fun updateItemsList(items: List<Entries>) {
        this.shopList.clear()
        this.shopList.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_grid_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val entrie = shopList[position]

        val url =
            if (entrie.items[0].images?.featured != null) entrie.items[0].images?.featured else entrie.items[0].images?.icon

        Glide.with(context)
            .load(url)
            .apply(RequestOptions().centerInside())
            .into(holder.itemView.imageviewFoto)

        holder.itemView.tv_name.text = entrie.items[0].name
        holder.itemView.tv_value.text = entrie.finalPrice.toString()
    }

    override fun getItemCount(): Int {
        return shopList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener { listener.itemSelected(shopList[adapterPosition]) }
        }
    }
}