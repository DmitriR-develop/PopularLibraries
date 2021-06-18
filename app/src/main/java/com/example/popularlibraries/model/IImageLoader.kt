package com.example.popularlibraries.model

interface IImageLoader<T> {
    fun loadIntro(url: String, container: T)
}