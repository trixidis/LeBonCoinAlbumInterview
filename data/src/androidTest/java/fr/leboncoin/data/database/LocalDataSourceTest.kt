package fr.leboncoin.data.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import fr.leboncoin.data.database.entity.TitleEntity
import fr.leboncoin.data.source.DataSource
import fr.leboncoin.data.source.LocalDataSource
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LocalDataSourceTest() {
    private lateinit var source: LocalDataSource
    private lateinit var db : AlbumsDatabase

    @Before
    fun setUp() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AlbumsDatabase::class.java
        ).build()
        source = LocalDataSource(ApplicationProvider.getApplicationContext(),db)
    }

    @After
    fun after() {
        db.close()
    }

    @Test
    fun shouldGiveNothingFromLocal() {
        runBlocking {
            val actual = mutableListOf<TitleEntity>()
            source.fetchTitles().collect {
                actual.addAll(it.getOrThrow())
            }
            Assert.assertEquals("should be empty", mutableListOf<TitleEntity>() ,actual)
        }
    }

    @Test
    fun shouldGiveOneElementAfterInsert() {
        runBlocking {
            source.addTitleToLocalStorage(
                TitleEntity(
                    1,
                    1,
                    "titre",
                    "https://leboncoin.fr/",
                    "https://leboncoin.fr/thumbnail"
                )
            )

            val actual = mutableListOf<TitleEntity>()
            source.fetchTitles().collect {
                actual.addAll(it.getOrThrow())
            }
            Assert.assertEquals("should have one element", 1 ,actual.count())
        }
    }


}