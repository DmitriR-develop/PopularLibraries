package com.example.popularlibraries.model

import com.example.popularlibraries.INetworkStatus
import com.example.popularlibraries.cache.IGithubUsersCache
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class RetrofitGithubUsersRepo @Inject constructor(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val githubUsersCache: IGithubUsersCache
) : GithubUsersRepo {
    override fun getUsers(): Single<List<GithubUser>> =
        networkStatus.isOnlineSingle()
            .flatMap { isOnline ->
                if (isOnline) {
                    api.getUsers()
                        .flatMap { users ->
                            githubUsersCache.putUsers(users).toSingleDefault(users)
                        }
                } else {
                    githubUsersCache.getUsers()
                }
            }.subscribeOn(Schedulers.io())
}