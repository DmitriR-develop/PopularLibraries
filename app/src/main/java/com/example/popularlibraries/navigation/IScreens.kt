package com.example.popularlibraries.navigation

import com.example.popularlibraries.model.GithubUser
import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users(): Screen
    fun user(user: GithubUser): Screen
}