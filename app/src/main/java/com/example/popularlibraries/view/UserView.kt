package com.example.popularlibraries.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface UserView : MvpView {
    fun init()
    fun setLogin(text: String)
    fun updateList()
}