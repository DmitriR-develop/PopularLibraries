package com.example.popularlibraries.room

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

@androidx.room.Database(
    entities = [RoomGithubUser::class, RoomGithubRepo::class],
    version = 1
)

abstract class Database : RoomDatabase() {
    abstract val userDao: IUserDao
    abstract val repositoryDao: IRepositoryDao

    companion object {
        const val DB_NAME = "database.db"
    }
}