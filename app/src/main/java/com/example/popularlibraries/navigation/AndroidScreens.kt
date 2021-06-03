package com.example.popularlibraries.navigation

import com.example.popularlibraries.fragments.UserFragment
import com.example.popularlibraries.fragments.UsersFragment
import com.example.popularlibraries.model.GithubUser
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens : IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }

    override fun user(user: GithubUser) = FragmentScreen { UserFragment.newInstance(user) }
}