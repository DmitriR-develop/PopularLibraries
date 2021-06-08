package com.example.popularlibraries.presenter

import com.example.popularlibraries.view.IItemView
import com.example.popularlibraries.view.UserItemView

interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(View: V)
    fun getCount(): Int
}

interface IUserListPresenter : IListPresenter<UserItemView>