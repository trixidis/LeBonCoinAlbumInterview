package fr.leboncoin.domain

import fr.leboncoin.data.database.entity.AlbumWithTitles
import fr.leboncoin.data.database.entity.TitleEntity
import fr.leboncoin.domain.use_cases.get_all_albums.GetAlbumsUseCase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class GetAlbumsUseCaseTest {


    private lateinit var useCase: GetAlbumsUseCase

    @Before
    fun setUp(){
        useCase = FakeUseCaseGetAlbumsImpl()
    }


    @Test
    fun giveAlbums(){
        runBlocking {
            val actual = mutableListOf<AlbumWithTitles>()
            useCase().collectLatest{
                actual.addAll(it.getOrThrow())
            }
            assertNotNull(actual)
            assert(actual.count()==1)
        }
    }

    @Test
    fun givesOnlyOneAlbum(){
        runBlocking {
            val actual = mutableListOf<AlbumWithTitles>()
            useCase().collectLatest{
                actual.addAll(it.getOrThrow())
            }
            assertNotNull(actual)
            assertFalse(actual.count()>1)
        }
    }

    @Test
    fun albumContainsATitle(){
        runBlocking {
            val expected =  TitleEntity(
                albumId = 1,
                id = 2,
                title = "reprehenderit est deserunt velit ipsam",
                url = "https://via.placeholder.com/600/771796",
                thumbnailUrl = "https://via.placeholder.com/150/771796"
            )
            val actual = mutableListOf<AlbumWithTitles>()
            useCase().collectLatest{
                actual.addAll(it.getOrThrow())
            }
            assertNotNull(actual)
            assertNotNull(actual.first().titles)
            assertEquals(expected, actual.first().titles.first())
        }
    }
}