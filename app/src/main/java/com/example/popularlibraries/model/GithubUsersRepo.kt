package com.example.popularlibraries.model

import io.reactivex.rxjava3.core.Observable

class GithubUsersRepo {
    private val repositories = Observable.just(
        GithubUser("login1"),
        GithubUser("login2"),
        GithubUser("login3"),
        GithubUser("login4"),
        GithubUser("login5")
    )

    fun getUsers(): Observable<GithubUser> = repositories
}