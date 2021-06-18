package com.example.popularlibraries.navigation

import com.example.popularlibraries.fragments.RepositoryFragment
import com.example.popularlibraries.fragments.UserFragment
import com.example.popularlibraries.fragments.UsersFragment
import com.example.popularlibraries.model.GitHubRepo
import com.example.popularlibraries.model.GithubUser
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen

object AndroidScreen {

    class UserScreens(private val user: GithubUser) : IScreens {
        override fun getFragment(): Screen = FragmentScreen { UserFragment.newInstance(user) }
    }

    class UsersScreens : IScreens {
        override fun getFragment(): Screen = FragmentScreen { UsersFragment.newInstance() }
    }

    class RepositoryScreens(private val repo: GitHubRepo) : IScreens {
        override fun getFragment(): Screen = FragmentScreen { RepositoryFragment.newInstance(repo) }
    }
}