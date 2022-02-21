package com.wolking.fortnite

import android.app.Application
import android.content.Context
import android.os.Environment
import androidx.multidex.MultiDex
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
//        injectKoin()
    }

//    private fun injectKoin() {
//        startKoin {
//            androidContext(this@App)
//            androidLogger()
//            modules(modulesList)
//        }
//    }

    companion object {
        val APP_PATH = "${Environment.getExternalStorageDirectory()}/fortnite"
    }
}