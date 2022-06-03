package com.wolking.fortnite.presentation.ui.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.wolking.fortnite.data.stats.data_source.StatsDto
import com.wolking.fortnite.databinding.AdapterStatsFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StatsTypeFragment(private val data: StatsDto?, private val title: String?) : Fragment() {
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
                if (data?.stats?.all?.solo != null) {
                    binding.tvScore.text = data.stats?.all?.solo?.score?.toInt().toString()
                    binding.tvWins.text = data.stats?.all?.solo?.wins?.toInt().toString()
                    binding.tvKills.text = data.stats?.all?.solo?.kills?.toInt().toString()
                    binding.tvDeaths.text = data.stats?.all?.solo?.deaths?.toInt().toString()
                    binding.tvMatches.text = data.stats?.all?.solo?.matches?.toInt().toString()
                    binding.tvKd.text = data.stats?.all?.solo?.kd.toString()

                    binding.tvTop1.text = data.stats?.all?.solo?.wins?.toInt().toString()

                    binding.tvTitleTop2.text = "Top10"
                    binding.tvTop2.text = data.stats?.all?.solo?.top10?.toInt().toString()
                    binding.tvTitleTop3.text = "Top25"

                    binding.tvTop3.text = data.stats?.all?.solo?.top25?.toInt().toString()
                }
            }
            "duo" -> {
                if (data?.stats?.all?.duo != null) {
                    binding.tvScore.text = data.stats?.all?.duo?.score?.toInt().toString()
                    binding.tvWins.text = data.stats?.all?.duo?.wins?.toInt().toString()
                    binding.tvKills.text = data.stats?.all?.duo?.kills?.toInt().toString()
                    binding.tvDeaths.text = data.stats?.all?.duo?.deaths?.toInt().toString()
                    binding.tvMatches.text = data.stats?.all?.duo?.matches?.toInt().toString()
                    binding.tvKd.text = data.stats?.all?.duo?.kd.toString()

                    binding.tvTop1.text = data.stats?.all?.duo?.wins?.toInt().toString()

                    binding.tvTitleTop2.text = "Top5"
                    binding.tvTop2.text = data.stats?.all?.duo?.top5?.toInt().toString()
                    binding.tvTitleTop3.text = "Top12"

                    binding.tvTop3.text = data.stats?.all?.duo?.top12?.toInt().toString()
                }
            }
            "trio" -> {
                if (data?.stats?.all?.trio != null) {
                    binding.tvScore.text = data.stats?.all?.trio?.score?.toInt().toString()
                    binding.tvWins.text = data.stats?.all?.trio?.wins?.toInt().toString()
                    binding.tvKills.text = data.stats?.all?.trio?.kills?.toInt().toString()
                    binding.tvDeaths.text = data.stats?.all?.trio?.deaths?.toInt().toString()
                    binding.tvMatches.text = data.stats?.all?.trio?.matches?.toInt().toString()
                    binding.tvKd.text = data.stats?.all?.trio?.kd.toString()

                    binding.tvTop1.text = data.stats?.all?.trio?.wins?.toInt().toString()

                    binding.tvTitleTop2.text = "Top3"
                    binding.tvTop2.text = data.stats?.all?.trio?.top3?.toInt().toString()
                    binding.tvTitleTop3.text = "Top6"

                    binding.tvTop3.text = data.stats?.all?.trio?.top6?.toInt().toString()
                }
            }
            "squad" -> {
                if (data?.stats?.all?.squad != null) {
                    binding.tvScore.text = data.stats?.all?.squad?.score?.toInt().toString()
                    binding.tvWins.text = data.stats?.all?.squad?.wins?.toInt().toString()
                    binding.tvKills.text = data.stats?.all?.squad?.kills?.toInt().toString()
                    binding.tvDeaths.text = data.stats?.all?.squad?.deaths?.toInt().toString()
                    binding.tvMatches.text = data.stats?.all?.squad?.matches?.toInt().toString()
                    binding.tvKd.text = data.stats?.all?.squad?.kd.toString()

                    binding.tvTop1.text = data.stats?.all?.squad?.wins?.toInt().toString()

                    binding.tvTitleTop2.text = "Top3"
                    binding.tvTop2.text = data.stats?.all?.squad?.top3?.toInt().toString()
                    binding.tvTitleTop3.text = "Top6"

                    binding.tvTop3.text = data.stats?.all?.squad?.top6?.toInt().toString()
                }
            }
        }
    }
}
