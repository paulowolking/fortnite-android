package com.wolking.fortnite.presentation.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.wolking.fortnite.presentation.cache.AppPreferences
import com.wolking.fortnite.presentation.NickActivity
import com.wolking.fortnite.R
import com.wolking.fortnite.databinding.FragmentHomeBinding
import com.wolking.fortnite.presentation.home.viewmodel.HomeViewModel
import com.wolking.fortnite.presentation.home.adapters.SimpleFragmentPagerAdapter
import com.wolking.fortnite.utils.load
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var fragmentPagerAdapter: SimpleFragmentPagerAdapter
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        registerObservers()
    }

    private fun setupView() {
        binding.ivPhoto.load(
            url = "https://robohash.org/${(0..100).random()}.png",
            circleCrop = true,
            errorImage = R.drawable.ic_launcher_background
        )

        binding.btEdit.setOnClickListener {
            requireContext().startActivity(Intent(activity, NickActivity::class.java))
        }
    }

    private fun registerObservers() {
        val nick = AppPreferences(requireContext()).getString("nick", null)
        homeViewModel.getStats(nick ?: "")
        homeViewModel.stats.observe(viewLifecycleOwner) {
            binding.progressBar.progress.isVisible = false

            binding.flBattlePass.isVisible = true
            binding.tvBattlePass.text = it.level.toString()
            binding.tvName.text = it.name
            binding.tvWins.text = it.wins.toString()
            binding.tvKd.text = it.kd.toString()
            binding.tvKills.text = it.kills?.toInt().toString()

            activity?.let { _ ->
                binding.viewpager.let { viewPager ->
                    fragmentPagerAdapter =
                        SimpleFragmentPagerAdapter(viewPager.context, childFragmentManager)
                    fragmentPagerAdapter.addFragment(
                        StatsTypeFragment(
                            it,
                            "solo"
                        ), "Solo"
                    )
                    fragmentPagerAdapter.addFragment(
                        StatsTypeFragment(it, "duo"),
                        "Duo"
                    )
                    fragmentPagerAdapter.addFragment(
                        StatsTypeFragment(it, "trio"),
                        "Trio"
                    )
                    fragmentPagerAdapter.addFragment(
                        StatsTypeFragment(it, "squad"),
                        "Squad"
                    )
                    viewPager.adapter = fragmentPagerAdapter
                    binding.tab.setupWithViewPager(binding.viewpager)
                }
            }
        }

        homeViewModel.loading.observe(viewLifecycleOwner) {
            binding.progressBar.progress.isVisible = it
        }
    }
}