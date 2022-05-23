package fr.leboncoin.domain.use_cases

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import fr.leboncoin.data.di.NetworkModule
import fr.leboncoin.data.entity.AlbumEntitiy
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
            val actual = mutableListOf<AlbumEntitiy>()
            useCase().collect{
                actual
                    .addAll(it.getOrThrow())
            }
            assertNotNull(actual)

            //Assuming that this a static json that wont change for the purpose of the exercise
            assert(actual.count()==100)
        }
    }
}