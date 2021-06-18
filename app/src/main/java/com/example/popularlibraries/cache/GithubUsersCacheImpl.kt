package com.example.popularlibraries.cache

import com.example.popularlibraries.model.GithubUser
import com.example.popularlibraries.room.Database
import com.example.popularlibraries.room.RoomGithubUser
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class GithubUsersCacheImpl(private val db: Database) : IGithubUsersCache {

    override fun getUsers(): Single<List<GithubUser>> =
        Single.fromCallable {
            db.userDao.getAll().map { roomUser ->
                GithubUser(roomUser.id, roomUser.login, roomUser.avatarUrl, roomUser.reposUrl)
            }
        }

    override fun putUsers(users: List<GithubUser>): Completable =
        Completable.fromAction {
            val roomUsers = users.map { user ->
                RoomGithubUser(
                    user.id ?: "",
                    user.login ?: "",
                    user.avatarUrl ?: "",
                    user.reposUrl ?: ""
                )
            }
            db.userDao.insert(roomUsers)
        }.subscribeOn(Schedulers.io())
}