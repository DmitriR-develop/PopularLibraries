package com.example.popularlibraries.modules

import com.example.popularlibraries.INetworkStatus
import com.example.popularlibraries.NetworkStatusImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface NetworkStateModule {
    @Singleton
    @Binds
    fun networkStatus(androidNetworkStatus: NetworkStatusImpl): INetworkStatus
}