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
import com.wolking.fortnite.data.models.shop.model.Entries
import com.wolking.fortnite.databinding.FragmentShopBinding
import com.wolking.fortnite.presentation.Resource
import com.wolking.fortnite.presentation.viewmodels.ShopViewModel
import com.wolking.fortnite.presentation.ui.shop.adapters.ItemAdapterListener
import com.wolking.fortnite.presentation.ui.shop.adapters.ItemGridAdapter
import com.wolking.fortnite.utils.SpacingItemDecoration
import com.wolking.fortnite.utils.Tools
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShopFragment : Fragment(), ItemAdapterListener {

    private val shopViewModel: ShopViewModel by viewModels()
    private lateinit var binding: FragmentShopBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShopBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        registerObservers()
    }

    fun setupView() {
        with(binding.rvShop) {
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
                    binding.progressBar.progress.isVisible = true
                }
                is Resource.Success -> {
                    binding.progressBar.progress.isVisible = false
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
                    (binding.rvShop.adapter as ItemGridAdapter)
                        .updateItemsList(entries)
                }
                is Resource.Error -> {
                    binding.progressBar.progress.isVisible = false
                    Log.e("Erro:", it.toString())
                }
            }
        })
    }

    override fun itemSelected(entries: Entries) {

    }


}