package com.example.popularlibraries.model

import com.example.popularlibraries.INetworkStatus
import com.example.popularlibraries.cache.IGithubRepositoriesCache
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubRepositoriesRepo(
    val api: IDataSource,
    val networkStatus: INetworkStatus,
    val githubReposCache: IGithubRepositoriesCache
) : IGithubRepositoriesRepo {
    override fun getRepositories(user: GithubUser): Single<List<GitHubRepo>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                user.reposUrl?.let { url ->
                    api.getRepo(url)
                        .flatMap { repos ->
                            githubReposCache.putUserRepos(user, repos).toSingleDefault(repos)
                        }
                } ?: Single.error<List<GitHubRepo>>(RuntimeException("No user in cache"))
                    .subscribeOn(Schedulers.io())
            } else {
                githubReposCache.getUserRepos(user)
            }
        }.subscribeOn(Schedulers.io())
}