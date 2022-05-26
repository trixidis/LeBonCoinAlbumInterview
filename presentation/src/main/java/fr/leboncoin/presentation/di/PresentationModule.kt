package fr.leboncoin.presentation.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import fr.leboncoin.data.repository.AlbumsRepository
import fr.leboncoin.domain.use_cases.get_all_albums.GetAlbumsUseCaseImpl
import fr.leboncoin.domain.use_cases.get_all_albums.GetAlbumsUseCase
import fr.leboncoin.domain.utils.NetworkMonitor
import fr.leboncoin.presentation.AlbumListViewModel

@Module
@InstallIn(ViewModelComponent::class)
object PresentationModule {

    @Provides
    fun provideDisplayTitle(monitor: NetworkMonitor,
    albumsRepository: AlbumsRepository): GetAlbumsUseCase {
        return GetAlbumsUseCaseImpl(albumsRepository,monitor)
    }


}