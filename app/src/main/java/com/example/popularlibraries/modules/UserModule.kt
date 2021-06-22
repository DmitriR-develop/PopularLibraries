package com.example.popularlibraries.modules

import android.content.Context
import androidx.room.Room
import com.example.popularlibraries.cache.GithubRepositoriesCacheImpl
import com.example.popularlibraries.cache.GithubUsersCacheImpl
import com.example.popularlibraries.cache.IGithubRepositoriesCache
import com.example.popularlibraries.cache.IGithubUsersCache
import com.example.popularlibraries.model.GithubUsersRepo
import com.example.popularlibraries.model.IGithubRepositoriesRepo
import com.example.popularlibraries.model.RetrofitGithubRepositoriesRepo
import com.example.popularlibraries.model.RetrofitGithubUsersRepo
import com.example.popularlibraries.room.Database
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
interface UserModule {
    @Singleton
    @Binds
    fun usersRepo(retrofitGithubUsersRepo: RetrofitGithubUsersRepo): GithubUsersRepo

    @Singleton
    @Binds
    fun usersCache(githubUsersCacheImpl: GithubUsersCacheImpl): IGithubUsersCache

    @Singleton
    @Binds
    fun githubReposCache(githubReposCacheImpl: GithubRepositoriesCacheImpl): IGithubRepositoriesCache

    @Singleton
    @Binds
    fun iGitReposRepo(retrofitGithubRepositoriesRepo: RetrofitGithubRepositoriesRepo): IGithubRepositoriesRepo

    companion object {
        @Singleton
        @Provides
        fun database(context: Context): Database =
            Room.databaseBuilder(context, Database::class.java, Database.DB_NAME).build()
    }
}