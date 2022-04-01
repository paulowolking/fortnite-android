package com.wolking.fortnite.presentation.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.wolking.fortnite.presentation.cache.AppPreferences
import com.wolking.fortnite.presentation.ui.NickActivity
import com.wolking.fortnite.R
import com.wolking.fortnite.databinding.FragmentHomeBinding
import com.wolking.fortnite.presentation.Resource
import com.wolking.fortnite.presentation.viewmodels.HomeViewModel
import com.wolking.fortnite.presentation.ui.home.adapters.SimpleFragmentPagerAdapter
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

        homeViewModel.stats.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {
                    binding.progressBar.progress.isVisible = true
                }
                is Resource.Success -> {
                    binding.progressBar.progress.isVisible = false

                    if (it.data.data?.battlePass != null) {
                        binding.flBattlePass.isVisible = true
                        binding.tvBattlePass.text = it.data.data?.battlePass?.level
                    }

                    binding.tvName.text = it.data.data?.account?.name
                    binding.tvWins.text =
                        it.data.data?.stats?.all?.overall?.wins?.toInt().toString()
                    binding.tvKd.text = it.data.data?.stats?.all?.overall?.kd.toString()
                    binding.tvKills.text =
                        it.data.data?.stats?.all?.overall?.kills?.toInt().toString()

                    activity?.let { activity ->
                        binding.viewpager.let { viewPager ->
                            fragmentPagerAdapter =
                                SimpleFragmentPagerAdapter(viewPager.context, childFragmentManager)
                            fragmentPagerAdapter.addFragment(
                                StatsTypeFragment(
                                    it.data.data,
                                    "solo"
                                ), "Solo"
                            )
                            fragmentPagerAdapter.addFragment(
                                StatsTypeFragment(it.data.data, "duo"),
                                "Duo"
                            )
                            fragmentPagerAdapter.addFragment(
                                StatsTypeFragment(it.data.data, "trio"),
                                "Trio"
                            )
                            fragmentPagerAdapter.addFragment(
                                StatsTypeFragment(it.data.data, "squad"),
                                "Squad"
                            )
                            viewPager.adapter = fragmentPagerAdapter
                            binding.tab.setupWithViewPager(binding.viewpager)
                        }
                    }
                }
                is Resource.Error -> {
                    binding.progressBar.progress.isVisible = false
                    Log.e("Erro:", it.toString())
                }
            }
        })
    }
}