package com.example.popularlibraries.modules

import com.example.popularlibraries.MainActivity
import com.example.popularlibraries.presenter.MainPresenter
import com.example.popularlibraries.presenter.RepositoryPresenter
import com.example.popularlibraries.presenter.UserPresenter
import com.example.popularlibraries.presenter.UsersPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetworkStateModule::class,
        UserModule::class
    ]
)

interface AppComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(usersPresenter: UsersPresenter)
    fun inject(userPresenter: UserPresenter)
    fun inject(repoPresenter: RepositoryPresenter)
}