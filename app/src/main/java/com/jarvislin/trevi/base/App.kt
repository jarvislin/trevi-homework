package com.jarvislin.trevi.base

import android.app.Application
import android.os.Build
import com.jarvislin.trevi.BuildConfig
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        if(BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}