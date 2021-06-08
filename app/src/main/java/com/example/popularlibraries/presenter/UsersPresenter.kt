package com.example.popularlibraries.presenter

import com.example.popularlibraries.model.GithubUser
import com.example.popularlibraries.model.GithubUsersRepo
import com.example.popularlibraries.navigation.UserScreens
import com.example.popularlibraries.view.UserItemView
import com.example.popularlibraries.view.UsersView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UsersPresenter(private val usersRepo: GithubUsersRepo, private val router: Router) :
    MvpPresenter<UsersView>() {
    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null
        override fun getCount() = users.size
        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
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
        val users = usersRepo.getUsers()
        usersListPresenter.users.addAll(users)
        viewState.updateList()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}