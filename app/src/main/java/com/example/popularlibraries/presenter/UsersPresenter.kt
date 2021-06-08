package com.example.popularlibraries.presenter

import android.widget.Toast
import com.example.popularlibraries.model.GithubUser
import com.example.popularlibraries.model.GithubUsersRepo
import com.example.popularlibraries.navigation.UserScreens
import com.example.popularlibraries.view.UserItemView
import com.example.popularlibraries.view.UsersView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Single
import moxy.MvpPresenter

class UsersPresenter(private val usersRepo: GithubUsersRepo, private val router: Router) :
    MvpPresenter<UsersView>() {
    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null
        override fun getCount() = users.size
        override fun bindView(view: UserItemView) {
            Single.just(users[view.pos]).subscribe({
                onBindViewSuccess(view, it.login)
            }, ::onBindViewError)
        }

        private fun onBindViewSuccess(view: UserItemView, login: String) {
            view.setLogin(login)
        }

        private fun onBindViewError(error: Throwable) {
        }
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        usersListPresenter.itemClickListener = { itemView ->
            val currentUser = usersListPresenter.users[itemView.pos]
            router.navigateTo(UserScreens().user(currentUser))
        }
    }

    fun loadData() {
        usersRepo.getUsers()
            .doOnComplete(::loadDataComplete)
            .subscribe(::loadDataSuccess)
    }

    private fun loadDataComplete() {
        viewState.updateList()
    }

    private fun loadDataSuccess(user: GithubUser) {
        usersListPresenter.users.add(user)
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}