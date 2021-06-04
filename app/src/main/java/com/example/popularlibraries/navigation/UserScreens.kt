package com.example.popularlibraries.navigation

import com.example.popularlibraries.fragments.UserFragment
import com.example.popularlibraries.model.GithubUser
import com.github.terrakok.cicerone.androidx.FragmentScreen

class UserScreens : IUserScreen {
    override fun user(user: GithubUser) = FragmentScreen { UserFragment.newInstance(user) }
}