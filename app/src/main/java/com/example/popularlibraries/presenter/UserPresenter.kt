package com.example.popularlibraries.presenter

import com.example.popularlibraries.model.GithubUser
import com.example.popularlibraries.view.UserView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UserPresenter(private val user: GithubUser, private val router: Router) :
    MvpPresenter<UserView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.showUser(user.login)
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}