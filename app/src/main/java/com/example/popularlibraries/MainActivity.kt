package com.example.popularlibraries

import android.os.Bundle
import com.example.popularlibraries.App.Navigation.navigatorHolder
import com.example.popularlibraries.App.Navigation.router
import com.example.popularlibraries.R.layout.activity_main
import com.example.popularlibraries.databinding.ActivityMainBinding
import com.example.popularlibraries.navigation.UsersScreens
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity

class MainActivity : MvpAppCompatActivity(activity_main) {

    val navigator = AppNavigator(this, R.id.container)
    private var vb: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        savedInstanceState ?: router.newRootScreen(UsersScreens().users())
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }
}