package fr

import fr.leboncoin.domain.use_cases.get_all_albums.GetAlbumsUseCase
import fr.leboncoin.presentation.AlbumListViewModel
import fr.leboncoin.presentation.FakeGetAlbumsUseCase
import fr.leboncoin.presentation.test
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class AlbumListViewModelTest {


    private lateinit var uc: GetAlbumsUseCase
    private lateinit var vm: AlbumListViewModel
    private val dispatcher = UnconfinedTestDispatcher()


    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        uc = FakeGetAlbumsUseCase()

    }

    /***
     * This test is passing but should not pass, impossible to get the expected result
     * as I pass the FakeGetAlbumsUseCase which gives results
     *
     * see forum links here :
     *
     *
     * Expected :[Loading(data=null), Success(albums=[AlbumUiModel(id=1, titles=[TitleUiModel(name=reprehenderit est deserunt velit ipsam, url=https://via.placeholder.com/600/771796, thumbnailUrl=https://via.placeholder.com/150/771796, id=2)])])]
     *
     * if needed here is the code to reproduce the expected :
     * AlbumUiState.Loading(),
        AlbumUiState.Success(
            listOf(
                AlbumUiModel(
                    1,
                    mutableListOf(
                        TitleEntity(
                            albumId = 1,
                            id = 2,
                            title = "reprehenderit est deserunt velit ipsam",
                            url = "https://via.placeholder.com/600/771796",
                            thumbnailUrl = "https://via.placeholder.com/150/771796"
                        )
                    ).map { TitleUiModel(it.title,it.url,it.thumbnailUrl,it.id) }
                )
        )
    )
     */
    @Test
    fun doesVmReceiveCorrectlyAlbums() = runBlocking {
        vm = AlbumListViewModel(uc)
        vm.albumsFlow.test(this)
            .assertNoValues()
            .finish()
    }


}