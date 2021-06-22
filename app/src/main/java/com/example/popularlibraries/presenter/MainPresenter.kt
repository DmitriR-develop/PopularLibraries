package com.example.popularlibraries.presenter

import com.example.popularlibraries.navigation.AndroidScreen
import com.example.popularlibraries.view.MainView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import javax.inject.Inject

class MainPresenter : MvpPresenter<MainView>() {

    @Inject
    lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(AndroidScreen.UsersScreens().getFragment())
    }

    fun backClicked() {
        router.exit()
    }
}