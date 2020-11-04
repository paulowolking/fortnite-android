package com.wolking.fortnite

import android.app.Application
import android.content.Context
import android.os.Environment
import androidx.multidex.MultiDex
import com.wolking.fortnite.di.modulesList
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        injectKoin()
    }

    private fun injectKoin() {
        startKoin {
            androidContext(this@App)
            androidLogger()
            modules(modulesList)
        }
    }

    companion object {
        val APP_PATH = "${Environment.getExternalStorageDirectory()}/fortnite"
    }
}