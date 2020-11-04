package com.wolking.fortnite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dimenuto.aboa.data.cache.AppPreferences
import com.wolking.fortnite.util.runDelayed
import org.jetbrains.anko.intentFor

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

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
        startActivity(intentFor<MainActivity>())
        finishAffinity()
    }

    private fun goToNick() {
        startActivity(intentFor<NickActivity>())
        finishAffinity()
    }
}
