package fr.leboncoin.presentation

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import fr.leboncoin.presentation.di.PresentationModule
import fr.leboncoin.presentation.ui.AlbumUiState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

class AlbumListViewModelTest{

    private lateinit var vm : AlbumListViewModel

    @Before
    fun setUp(){
        vm = AlbumListViewModel(PresentationModule.provideDisplayTitle(ApplicationProvider.getApplicationContext()))
    }

    @Test
    fun doesVmReceiveCorrectlyAlbums()=  runBlocking{
            val actual = mutableListOf<AlbumUiState>()

            val job = launch {
                vm.albumsFlow.toList(actual)
            }

            assertNotNull(actual)

            //Assuming that this a static json that wont change for the purpose of the exercise
            assert(actual.count()>0)

            job.cancel()
        }






}