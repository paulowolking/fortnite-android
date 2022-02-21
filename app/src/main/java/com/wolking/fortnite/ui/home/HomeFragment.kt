package com.wolking.fortnite.ui.home

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
import com.wolking.fortnite.NickActivity
import com.wolking.fortnite.R
import com.wolking.fortnite.presentation.Resource
import com.wolking.fortnite.presentation.cache.AppPreferences
import com.wolking.fortnite.presentation.viewmodels.HomeViewModel
import com.wolking.fortnite.ui.home.adapters.SimpleFragmentPagerAdapter
import com.wolking.fortnite.util.load
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.progress_bar.*

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var fragmentPagerAdapter: SimpleFragmentPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        registerObservers()
    }

    fun setupView() {
        iv_photo.load(
            url = "https://robohash.org/${(0..100).random()}.png",
            circleCrop = true,
            errorImage = R.drawable.ic_launcher_background
        )

        bt_edit.setOnClickListener {
            requireContext().startActivity(Intent(activity, NickActivity::class.java))
        }
    }

    private fun registerObservers() {
        val nick = AppPreferences(requireContext()).getString("nick", null)
        homeViewModel.getStats(true, nick ?: "")

        homeViewModel.stats.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {
                    progress.isVisible = true
                }
                is Resource.Success -> {
                    progress.isVisible = false

                    if (it.data.data?.battlePass != null) {
                        fl_battle_pass.isVisible = true
                        tv_battle_pass.text = it.data.data?.battlePass?.level
                    }

                    tv_name.text = it.data.data?.account?.name
                    tv_wins.text = it.data.data?.stats?.all?.overall?.wins?.toInt().toString()
                    tv_kd.text = it.data.data?.stats?.all?.overall?.kd.toString()
                    tv_kills.text = it.data.data?.stats?.all?.overall?.kills?.toInt().toString()

                    activity?.let { activity ->
                        viewpager?.let { viewPager ->
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
                            tab?.setupWithViewPager(viewpager)
                        }
                    }
                }
                is Resource.Failure -> {
                    progress.isVisible = false
                    Log.e("Erro:", it.throwable.toString())
                }
            }
        })
    }
}