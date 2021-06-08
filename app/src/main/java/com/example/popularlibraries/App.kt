package com.example.popularlibraries

import android.app.Application
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router

class App : Application() {
    object Navigation {
        private val cicerone by lazy { Cicerone.create() }

        val router get() = cicerone.router

        val navigatorHolder get() = cicerone.getNavigatorHolder()
    }
}