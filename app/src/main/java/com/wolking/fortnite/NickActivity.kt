package com.wolking.fortnite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import com.dimenuto.aboa.data.cache.AppPreferences
import com.wolking.fortnite.presentation.Resource
import com.wolking.fortnite.presentation.viewmodels.HomeViewModel
import kotlinx.android.synthetic.main.activity_nick.*
import org.jetbrains.anko.intentFor
import org.koin.android.viewmodel.ext.android.viewModel

class NickActivity : AppCompatActivity() {

    private val homeViewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nick)

        bt_search.setOnClickListener {
            val nick = et_nick.text.toString()
            if (TextUtils.isEmpty(nick)) {
                Toast.makeText(this, "Digita teu nick", Toast.LENGTH_SHORT).show()
            } else {
                search(nick)
            }
        }
    }

    private fun search(nick: String) {
        homeViewModel.getStats(true, nick)

        homeViewModel.stats.observe(this, Observer {
            when (it) {
                is Resource.Loading -> {
                    //
                }
                is Resource.Success -> {
                    AppPreferences(this).setString("nick", et_nick.text.toString())
                    goToMain()
                }
                is Resource.Failure -> {
                    Toast.makeText(this, "Hum..., não encontrei você.", Toast.LENGTH_SHORT).show()
                    Log.e("Erro:", it.throwable.toString())
                }
            }
        })
    }

    private fun goToMain() {
        startActivity(intentFor<MainActivity>())
        finishAffinity()
    }
}