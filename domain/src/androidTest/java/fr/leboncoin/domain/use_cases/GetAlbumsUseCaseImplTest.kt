package fr.leboncoin.domain.use_cases

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import fr.leboncoin.data.di.NetworkModule
import fr.leboncoin.data.database.entity.AlbumWithTitles
import fr.leboncoin.domain.use_cases.get_all_albums.GetAlbumsUseCase
import fr.leboncoin.domain.use_cases.get_all_albums.GetAlbumsUseCaseImpl
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GetAlbumsUseCaseImplTest{

    private lateinit var useCase: GetAlbumsUseCase

    @Before
    fun setUp(){
        useCase = GetAlbumsUseCaseImpl(NetworkModule.provideAlbumsRepository(ApplicationProvider.getApplicationContext()))
    }


    @Test
    fun giveAlbums(){
        runBlocking {
            val actual = mutableListOf<AlbumWithTitles>()
            useCase().collectLatest{
                actual.addAll(it.getOrThrow())
            }
            assertNotNull(actual)

            //Assuming that this a static json that wont change for the purpose of the exercise
            assert(actual.count()==100)
        }
    }
}