package com.vintsarevich.secondlife

import android.app.Application
import android.support.v7.app.AppCompatDelegate

class SecondLifeApplication : Application() {
    companion object {
        lateinit var instance: SecondLifeApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

}