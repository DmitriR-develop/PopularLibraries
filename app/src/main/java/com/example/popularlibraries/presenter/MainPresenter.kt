package com.example.popularlibraries.presenter

import com.example.popularlibraries.navigation.AndroidScreen
import com.example.popularlibraries.view.MainView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class MainPresenter(val router: Router) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(AndroidScreen.UsersScreens().getFragment())
    }

    fun backClicked() {
        router.exit()
    }
}