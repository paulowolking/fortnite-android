package com.wolking.fortnite.presentation.shop.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.wolking.fortnite.R
import com.wolking.fortnite.domain.shop.model.Shop
import kotlinx.android.synthetic.main.adapter_grid_item.view.*


class ItemGridAdapter(
    private var context: Context,
    private var shopList: MutableList<Shop> = mutableListOf(),
    private val listener: ItemAdapterListener
) : RecyclerView.Adapter<ItemGridAdapter.ViewHolder>() {

    constructor(
        context: Context, listener: ItemAdapterListener
    ) : this(
        context, mutableListOf<Shop>(), listener = listener
    )

    fun updateItemsList(items: List<Shop>) {
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
        val itemShop = shopList[position]

        Glide.with(context)
            .load(itemShop.photoUrl)
            .apply(RequestOptions().centerInside())
            .into(holder.itemView.imageviewFoto)

        holder.itemView.tv_name.text = itemShop.title
        holder.itemView.tv_value.text = itemShop.value.toString()
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