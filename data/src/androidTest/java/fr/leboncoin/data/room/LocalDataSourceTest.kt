package fr.leboncoin.data.room

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import fr.leboncoin.data.entity.TitleEntity
import fr.leboncoin.data.source.LocalDataSource
import fr.leboncoin.data.source.TitleDataSource
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LocalDataSourceTest() {
    private lateinit var source : TitleDataSource

    @Before
    fun setUp(){
        source = LocalDataSource(ApplicationProvider.getApplicationContext())
    }

    @After
    fun after(){

    }

    @Test
     fun shouldGiveNothingFromLocal(){
        runBlocking {
            val actual = mutableListOf<TitleEntity>()
            source.fetchTitles().collect{
                actual.add(it)
            }
            MatcherAssert.assertThat("should be empty",actual.isEmpty())
        }
    }



}