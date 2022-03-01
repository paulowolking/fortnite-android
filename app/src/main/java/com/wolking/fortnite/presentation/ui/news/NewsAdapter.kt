package com.wolking.fortnite.presentation.ui.news

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.wolking.fortnite.R
import com.wolking.fortnite.data.models.news.model.Motd
import kotlinx.android.synthetic.main.adapter_grid_item.view.imageviewFoto
import kotlinx.android.synthetic.main.adapter_grid_item.view.tv_name
import kotlinx.android.synthetic.main.adapter_news.view.*
import okhttp3.internal.notify


class NewsAdapter(
    private var context: Context,
    private var motdsList: MutableList<Motd> = mutableListOf()
) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    constructor(
        context: Context
    ) : this(
        context, mutableListOf<Motd>()
    )

    fun updateItemsList(items: List<Motd>) {
        this.motdsList.clear()
        this.motdsList.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_news, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val motd = motdsList[position]

        Glide.with(context)
            .load(motd.image)
            .apply(RequestOptions().centerInside())
            .into(holder.itemView.imageviewFoto)

        holder.itemView.tv_name.text = motd.title
        holder.itemView.tv_description.text = motd.body
    }

    override fun getItemCount(): Int {
        return motdsList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
//            itemView.setOnClickListener { listener.itemSelected(shopList[adapterPosition]) }
        }
    }
}