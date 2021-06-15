package com.example.popularlibraries.model

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUsersRepo(val api: IDataSource) : GithubUsersRepo {
    override fun getUsers(): Single<List<GithubUser>> =
        api.getUsers().subscribeOn(Schedulers.io()) ?: Single
            .error<List<GithubUser>>(RuntimeException("Users not load"))
            .subscribeOn(Schedulers.io())
}