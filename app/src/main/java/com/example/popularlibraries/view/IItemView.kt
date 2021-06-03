package com.example.popularlibraries.view

interface IItemView {
    var pos: Int
}

interface UserItemView : IItemView {
    fun setLogin(text: String)
}