package com.wolking.fortnite.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.wolking.fortnite.R
import com.wolking.fortnite.databinding.ActivityWelcomeBinding
import com.wolking.fortnite.presentation.cache.AppPreferences
import com.wolking.fortnite.utils.runDelayed

class WelcomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_welcome)

        runDelayed {
            val nick = AppPreferences(this).getString("nick", null)

            if (nick != null) {
                goToMain()
            } else {
                goToNick()
            }
        }
    }

    private fun goToMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finishAffinity()
    }

    private fun goToNick() {
        startActivity(Intent(this, NickActivity::class.java))
        finishAffinity()
    }
}
