package fr.leboncoin.data.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import fr.leboncoin.data.database.entity.TitleEntity
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Assert
import org.junit.Before

import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class AlbumsDatabaseTest {
    private lateinit var titlesDao: TitleDao
    private lateinit var db: AlbumsDatabase

    @Before
    fun setUp() {
        val context: Context = ApplicationProvider.getApplicationContext()
        db = Room.inMemoryDatabaseBuilder(
            context, AlbumsDatabase::class.java
        ).build()
        titlesDao = db.getTitlesDao()
    }


    @Test
    fun writeAndReadTitle() = runBlocking {
        val title = TitleEntity(
            albumId = 30,
            id = 2,
            title = "reprehenderit est deserunt velit ipsam",
            url = "https://via.placeholder.com/600/771796",
            thumbnailUrl = "https://via.placeholder.com/150/771796"
        )
        titlesDao.addTitle(title)
        val titles = titlesDao.getEveryTitles()
        Assert.assertTrue("item should be contained in list", titles.contains(title))
    }


    @Test
    fun DontwriteAndReadTitleShouldBeEmpty() = runBlocking {

        val title = TitleEntity(
            albumId = 30,
            id = 2,
            title = "reprehenderit est deserunt velit ipsam",
            url = "https://via.placeholder.com/600/771796",
            thumbnailUrl = "https://via.placeholder.com/150/771796"
        )

        val titles = titlesDao.getEveryTitles()
        Assert.assertTrue("item should not be  contained in list", !titles.contains(title))
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }
}