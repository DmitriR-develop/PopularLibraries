package com.example.popularlibraries.navigation

import com.example.popularlibraries.fragments.UsersFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

class UsersScreens : IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }
}