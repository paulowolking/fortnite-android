package com.wolking.fortnite.presentation.shop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.wolking.fortnite.databinding.FragmentShopBinding
import com.wolking.fortnite.domain.shop.model.Shop
import com.wolking.fortnite.presentation.shop.viewmodel.ShopViewModel
import com.wolking.fortnite.presentation.shop.adapters.ItemAdapterListener
import com.wolking.fortnite.presentation.shop.adapters.ItemGridAdapter
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

    private fun setupView() {
        with(binding.rvShop) {
            layoutManager = GridLayoutManager(context, 2)
            addItemDecoration(SpacingItemDecoration(2, Tools.dpToPx(context, 2), false))
            adapter = ItemGridAdapter(context, this@ShopFragment)
        }
    }

    private fun registerObservers() {
        shopViewModel.getShop()
        shopViewModel.items.observe(viewLifecycleOwner) {
            (binding.rvShop.adapter as ItemGridAdapter)
                .updateItemsList(it)
        }

        shopViewModel.loading.observe(viewLifecycleOwner) {
            binding.progressBar.progress.isVisible = it
        }
    }

    override fun itemSelected(itemShop: Shop) {

    }
}