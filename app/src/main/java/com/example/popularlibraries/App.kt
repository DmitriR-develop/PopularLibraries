package com.example.popularlibraries

import android.app.Application
import com.example.popularlibraries.modules.AppComponent
import com.example.popularlibraries.modules.AppModule
import com.example.popularlibraries.modules.DaggerAppComponent

class App : Application() {
    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}