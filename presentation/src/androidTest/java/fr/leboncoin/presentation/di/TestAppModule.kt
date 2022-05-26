package fr.leboncoin.presentation.di

import dagger.Module
import dagger.Provides
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.testing.TestInstallIn
import fr.leboncoin.domain.use_cases.get_all_albums.GetAlbumsUseCase
import fr.leboncoin.presentation.AlbumListViewModel
import fr.leboncoin.presentation.FakeGetAlbumsUseCase

@TestInstallIn(
    components = [ViewModelComponent::class],
    replaces = [PresentationModule::class]
)
@Module
object FakePresentationModule {

    @Provides
    fun providesFakeUseCaseImpl():GetAlbumsUseCase{
        return FakeGetAlbumsUseCase()
    }

    @Provides
    fun providesFakeAlbumViewModel(useCase: GetAlbumsUseCase):AlbumListViewModel{
        return AlbumListViewModel(useCase)
    }
}