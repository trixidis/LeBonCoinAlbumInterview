package fr.leboncoin.data.repository

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import fr.leboncoin.data.di.NetworkModule
import fr.leboncoin.data.entity.TitleEntity
import fr.leboncoin.data.source.LocalDataSource
import fr.leboncoin.data.source.RemoteDataSource
import fr.leboncoin.data.source.TitleDataSource
import fr.leboncoin.data.utils.NetworkMonitor
import kotlinx.coroutines.flow.collect
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
    private val localSource = LocalDataSource(ApplicationProvider.getApplicationContext())
    private val fakeNetwork = object : NetworkMonitor{
        override fun hasNetwork(): Boolean {
            return true
        }

    }

    @Before
    fun setUp(){
        repo = AlbumsRepositoryImpl(remoteDataSource = source,localDataSource = localSource,monitor = fakeNetwork)
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
    fun giveTitles(){
        runBlocking {
            val actual = mutableListOf<TitleEntity>()
            repo.getTitles().collect{
                actual
                    .add(it)
            }
            assertNotNull(actual)

            //Assuming that this a static json that wont change for the purpose of the exercise
            assert(actual.count()==5000)
        }
    }
}