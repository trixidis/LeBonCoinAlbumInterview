package fr.leboncoin.presentation.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import fr.leboncoin.data.di.NetworkModule
import fr.leboncoin.domain.use_cases.DisplayAlbumsImpl
import fr.leboncoin.domain.use_cases.UseCaseDisplayAlbums

@Module
@InstallIn(ViewModelComponent::class)
object PresentationModule {

    @Provides
    fun provideDisplayTitle(@ApplicationContext appContext: Context): UseCaseDisplayAlbums {
        return DisplayAlbumsImpl(NetworkModule.provideAlbumsRepository(appContext))
    }
}