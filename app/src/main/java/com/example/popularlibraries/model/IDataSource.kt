package com.example.popularlibraries.model

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface IDataSource {

    @GET("/users")
    fun getUsers(): Single<List<GithubUser>>

    @GET
    fun getRepo(@Url url: String): Single<List<GitHubRepo>>
}