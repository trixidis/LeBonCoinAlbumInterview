package fr.leboncoin.domain.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


import android.content.Context
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import fr.leboncoin.domain.utils.NetworkMonitor
import fr.leboncoin.domain.utils.NetworkMonitorImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    fun provideNetworkMonitor(@ApplicationContext appContext: Context): NetworkMonitor {
        return NetworkMonitorImpl(appContext)
    }

}