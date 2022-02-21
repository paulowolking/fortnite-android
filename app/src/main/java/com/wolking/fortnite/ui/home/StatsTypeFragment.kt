package com.wolking.fortnite.ui.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.wolking.fortnite.R
import com.wolking.fortnite.data.model.Stats
import kotlinx.android.synthetic.main.adapter_stats_fragment.*

class StatsTypeFragment(private val data: Stats?, private val title: String?) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.adapter_stats_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.setupViews()
        this.load()
    }

    fun load() {
        when (title) {
            "solo" -> {
                if (data?.stats?.all?.solo != null) {
                    tv_score.text = data.stats?.all?.solo?.score?.toInt().toString()
                    tv_wins.text = data.stats?.all?.solo?.wins?.toInt().toString()
                    tv_kills.text = data.stats?.all?.solo?.kills?.toInt().toString()
                    tv_deaths.text = data.stats?.all?.solo?.deaths?.toInt().toString()
                    tv_matches.text = data.stats?.all?.solo?.matches?.toInt().toString()
                    tv_kd.text = data.stats?.all?.solo?.kd.toString()

                    tv_top1.text = data.stats?.all?.solo?.wins?.toInt().toString()

                    tv_title_top2.text = "Top10"
                    tv_top2.text = data.stats?.all?.solo?.top10?.toInt().toString()
                    tv_title_top3.text = "Top25"

                    tv_top3.text = data.stats?.all?.solo?.top25?.toInt().toString()
                }
            }
            "duo" -> {
                if (data?.stats?.all?.duo != null) {
                    tv_score.text = data.stats?.all?.duo?.score?.toInt().toString()
                    tv_wins.text = data.stats?.all?.duo?.wins?.toInt().toString()
                    tv_kills.text = data.stats?.all?.duo?.kills?.toInt().toString()
                    tv_deaths.text = data.stats?.all?.duo?.deaths?.toInt().toString()
                    tv_matches.text = data.stats?.all?.duo?.matches?.toInt().toString()
                    tv_kd.text = data.stats?.all?.duo?.kd.toString()

                    tv_top1.text = data.stats?.all?.duo?.wins?.toInt().toString()

                    tv_title_top2.text = "Top5"
                    tv_top2.text = data.stats?.all?.duo?.top5?.toInt().toString()
                    tv_title_top3.text = "Top12"

                    tv_top3.text = data.stats?.all?.duo?.top12?.toInt().toString()
                }
            }
            "trio" -> {
                if (data?.stats?.all?.trio != null) {
                    tv_score.text = data.stats?.all?.trio?.score?.toInt().toString()
                    tv_wins.text = data.stats?.all?.trio?.wins?.toInt().toString()
                    tv_kills.text = data.stats?.all?.trio?.kills?.toInt().toString()
                    tv_deaths.text = data.stats?.all?.trio?.deaths?.toInt().toString()
                    tv_matches.text = data.stats?.all?.trio?.matches?.toInt().toString()
                    tv_kd.text = data.stats?.all?.trio?.kd.toString()

                    tv_top1.text = data.stats?.all?.trio?.wins?.toInt().toString()

                    tv_title_top2.text = "Top3"
                    tv_top2.text = data.stats?.all?.trio?.top3?.toInt().toString()
                    tv_title_top3.text = "Top6"

                    tv_top3.text = data.stats?.all?.trio?.top6?.toInt().toString()
                }
            }
            "squad" -> {
                if (data?.stats?.all?.squad != null) {
                    tv_score.text = data.stats?.all?.squad?.score?.toInt().toString()
                    tv_wins.text = data.stats?.all?.squad?.wins?.toInt().toString()
                    tv_kills.text = data.stats?.all?.squad?.kills?.toInt().toString()
                    tv_deaths.text = data.stats?.all?.squad?.deaths?.toInt().toString()
                    tv_matches.text = data.stats?.all?.squad?.matches?.toInt().toString()
                    tv_kd.text = data.stats?.all?.squad?.kd.toString()

                    tv_top1.text = data.stats?.all?.squad?.wins?.toInt().toString()

                    tv_title_top2.text = "Top3"
                    tv_top2.text = data.stats?.all?.squad?.top3?.toInt().toString()
                    tv_title_top3.text = "Top6"

                    tv_top3.text = data.stats?.all?.squad?.top6?.toInt().toString()
                }
            }
        }
    }

    fun setupViews() {

    }
}
