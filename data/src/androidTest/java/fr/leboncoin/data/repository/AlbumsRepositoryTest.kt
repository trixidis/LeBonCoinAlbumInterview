package fr.leboncoin.data.repository

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import fr.leboncoin.data.database.AlbumsDatabase
import fr.leboncoin.data.di.NetworkModule
import fr.leboncoin.data.database.entity.AlbumEntity
import fr.leboncoin.data.database.entity.AlbumWithTitles
import fr.leboncoin.data.source.LocalDataSource
import fr.leboncoin.data.source.RemoteDataSource
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class AlbumsRepositoryTest{
    lateinit var repo : AlbumsRepositoryImpl
    private val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()
    private val source = RemoteDataSource(NetworkModule.provideAlbumService(client))

    private val db  = Room.inMemoryDatabaseBuilder(
        ApplicationProvider.getApplicationContext(),
        AlbumsDatabase::class.java
    ).build()
    private val localSource = LocalDataSource(ApplicationProvider.getApplicationContext(),db)

    @Before
    fun setUp(){
        repo = AlbumsRepositoryImpl(remoteDataSource = source,localDataSource = localSource)
    }

    @After
    fun after(){

    }

    @Test
    fun isDependencyInjectionOk(){
        assertNotNull(repo.localDataSource)
        assertNotNull(repo.remoteDataSource)
    }

    @Test
    fun giveAlbums(){
        runBlocking {
            val actual = mutableListOf<AlbumWithTitles>()
            repo.remoteDataSource.fetchAlbumsWithTitles().collect{
                actual
                    .addAll(it.getOrThrow())
            }
            assertNotNull(actual)

            //Assuming that this a static json that wont change for the purpose of the exercise
            assert(actual.count()==100)
        }
    }



    @Test
    fun giveAlbumsAndTitles(){
        runBlocking {
            val actual = mutableListOf<AlbumWithTitles>()
            repo.remoteDataSource.fetchAlbumsWithTitles().collect{
                actual
                    .addAll(it.getOrThrow())
            }
            assertNotNull(actual)
            assert(actual.count()==100)


            //Assuming that this a static json that wont change for the purpose of the exercise
            assert(actual.first().titles.count()==50)
        }
    }

}