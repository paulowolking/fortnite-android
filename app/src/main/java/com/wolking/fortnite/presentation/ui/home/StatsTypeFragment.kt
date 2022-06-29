package com.wolking.fortnite.presentation.ui.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.wolking.fortnite.databinding.AdapterStatsFragmentBinding
import com.wolking.fortnite.domain.stats.model.Stats
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StatsTypeFragment(private val data: Stats?, private val title: String?) : Fragment() {
    private lateinit var binding: AdapterStatsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AdapterStatsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.load()
    }

    fun load() {
        when (title) {
            "solo" -> {
                data?.solo?.let {
                    binding.tvScore.text = it.score?.toInt().toString()
                    binding.tvWins.text = it.wins?.toInt().toString()
                    binding.tvKills.text = it.kills?.toInt().toString()
                    binding.tvDeaths.text = it.deaths?.toInt().toString()
                    binding.tvMatches.text = it.matches?.toInt().toString()
                    binding.tvKd.text = it.kd.toString()
                    binding.tvTop1.text = it.wins?.toInt().toString()
                    binding.tvTitleTop2.text = "Top10"
                    binding.tvTop2.text = it.top10?.toInt().toString()
                    binding.tvTitleTop3.text = "Top25"
                    binding.tvTop3.text = it.top25?.toInt().toString()
                }
            }
            "duo" -> {
                data?.duo?.let {
                    binding.tvScore.text = it.score?.toInt().toString()
                    binding.tvWins.text = it.wins?.toInt().toString()
                    binding.tvKills.text = it.kills?.toInt().toString()
                    binding.tvDeaths.text = it.deaths?.toInt().toString()
                    binding.tvMatches.text = it.matches?.toInt().toString()
                    binding.tvKd.text = it.kd.toString()
                    binding.tvTop1.text = it.wins?.toInt().toString()
                    binding.tvTitleTop2.text = "Top5"
                    binding.tvTop2.text = it.top5?.toInt().toString()
                    binding.tvTitleTop3.text = "Top12"
                    binding.tvTop3.text = it.top12?.toInt().toString()
                }
            }
            "trio" -> {
                data?.trio?.let {
                    binding.tvScore.text = it.score?.toInt().toString()
                    binding.tvWins.text = it.wins?.toInt().toString()
                    binding.tvKills.text = it.kills?.toInt().toString()
                    binding.tvDeaths.text = it.deaths?.toInt().toString()
                    binding.tvMatches.text = it.matches?.toInt().toString()
                    binding.tvKd.text = it.kd.toString()
                    binding.tvTop1.text = it.wins?.toInt().toString()
                    binding.tvTitleTop2.text = "Top3"
                    binding.tvTop2.text = it.top3?.toInt().toString()
                    binding.tvTitleTop3.text = "Top6"
                    binding.tvTop3.text = it.top6?.toInt().toString()
                }
            }
            "squad" -> {
                data?.squad?.let {
                    binding.tvScore.text = it.score?.toInt().toString()
                    binding.tvWins.text = it.wins?.toInt().toString()
                    binding.tvKills.text = it.kills?.toInt().toString()
                    binding.tvDeaths.text = it.deaths?.toInt().toString()
                    binding.tvMatches.text = it.matches?.toInt().toString()
                    binding.tvKd.text = it.kd.toString()
                    binding.tvTop1.text = it.wins?.toInt().toString()
                    binding.tvTitleTop2.text = "Top3"
                    binding.tvTop2.text = it.top3?.toInt().toString()
                    binding.tvTitleTop3.text = "Top6"
                    binding.tvTop3.text = it.top6?.toInt().toString()
                }
            }
        }
    }
}
