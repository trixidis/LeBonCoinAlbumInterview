package fr.leboncoin.presentation.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import fr.leboncoin.data.di.NetworkModule
import fr.leboncoin.domain.use_cases.GetAlbumsUseCaseImpl
import fr.leboncoin.domain.use_cases.GetAlbumsUseCase

@Module
@InstallIn(ViewModelComponent::class)
object PresentationModule {

    @Provides
    fun provideDisplayTitle(@ApplicationContext appContext: Context): GetAlbumsUseCase {
        return GetAlbumsUseCaseImpl(NetworkModule.provideAlbumsRepository(appContext))
    }
}