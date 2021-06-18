package com.example.popularlibraries.cache

import com.example.popularlibraries.model.GitHubRepo
import com.example.popularlibraries.model.GithubUser
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IGithubRepositoriesCache {

    fun getUserRepos(user: GithubUser): Single<List<GitHubRepo>>
    fun putUserRepos(user: GithubUser, repos: List<GitHubRepo>): Completable
}