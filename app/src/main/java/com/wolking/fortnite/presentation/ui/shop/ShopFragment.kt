package com.wolking.fortnite.presentation.ui.shop

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.wolking.fortnite.R
import com.wolking.fortnite.data.models.shop.model.Entries
import com.wolking.fortnite.presentation.Resource
import com.wolking.fortnite.presentation.viewmodels.ShopViewModel
import com.wolking.fortnite.presentation.ui.shop.adapters.ItemAdapterListener
import com.wolking.fortnite.presentation.ui.shop.adapters.ItemGridAdapter
import com.wolking.fortnite.utils.SpacingItemDecoration
import com.wolking.fortnite.utils.Tools
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_shop.*
import kotlinx.android.synthetic.main.progress_bar.*

@AndroidEntryPoint
class ShopFragment : Fragment(), ItemAdapterListener {

    private val shopViewModel: ShopViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_shop, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        registerObservers()
    }

    fun setupView() {
        with(rv_items) {
            layoutManager = GridLayoutManager(context, 2)
            addItemDecoration(SpacingItemDecoration(2, Tools.dpToPx(context, 2), false))
            adapter = ItemGridAdapter(context, this@ShopFragment)
        }
    }

    private fun registerObservers() {
        shopViewModel.getShop()

        shopViewModel.shop.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {
                    progress.isVisible = true
                }
                is Resource.Success -> {
                    progress.isVisible = false
                    val entries: MutableList<Entries> = mutableListOf()
                    it.data.data?.featured?.entries?.let {
                        entries.addAll(it)
                    }

                    it.data.data?.daily?.entries?.let {
                        entries.addAll(it)
                    }

                    it.data.data?.specialFeatured?.entries?.let {
                        entries.addAll(it)
                    }
                    (rv_items?.adapter as ItemGridAdapter)
                        .updateItemsList(entries)
                }
                is Resource.Failure -> {
                    progress.isVisible = false
                    Log.e("Erro:", it.throwable.toString())
                }
            }
        })
    }

    override fun itemSelected(entries: Entries) {

    }


}