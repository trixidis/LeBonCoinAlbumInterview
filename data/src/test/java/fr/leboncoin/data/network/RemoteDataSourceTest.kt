package fr.leboncoin.data.network

import android.accounts.NetworkErrorException
import fr.leboncoin.data.database.entity.TitleEntity
import fr.leboncoin.data.serializer.defaultConverter
import fr.leboncoin.data.source.RemoteDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.ExperimentalSerializationApi
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Retrofit
import java.net.SocketTimeoutException
import java.util.concurrent.TimeUnit

@ExperimentalCoroutinesApi
@ExperimentalSerializationApi
class RemoteDataSourceTest {
    private val mockWebServer = MockWebServer()

    private val client = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.SECONDS)
        .readTimeout(1, TimeUnit.SECONDS)
        .writeTimeout(1, TimeUnit.SECONDS)
        .build()

    private val api = Retrofit.Builder()
        .baseUrl(mockWebServer.url("/"))
        .client(client)
        .addConverterFactory(defaultConverter())
        .build()
        .create(AlbumService::class.java)

    private val source = RemoteDataSource(api)

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `should fetch one title correctly given 200 response`() {
        mockWebServer.enqueue(
            MockResponse().setResponseCode(200).setBody(
                "" +
                        " [ " +
                        " {\n" +
                        "    \"albumId\": 30,\n" +
                        "    \"id\": 2,\n" +
                        "    \"title\": \"reprehenderit est deserunt velit ipsam\",\n" +
                        "    \"url\": \"https://via.placeholder.com/600/771796\",\n" +
                        "    \"thumbnailUrl\": \"https://via.placeholder.com/150/771796\"\n" +
                        "  }" +
                        "]"
            )
        )

        runBlocking {
            val actual = source.fetchTitles().first()


            val expected = Result.success(
                listOf(
                    TitleEntity(
                        albumId = 30,
                        id = 2,
                        title = "reprehenderit est deserunt velit ipsam",
                        url = "https://via.placeholder.com/600/771796",
                        thumbnailUrl = "https://via.placeholder.com/150/771796"
                    )
                )
            )


            Assert.assertEquals(expected, actual)
        }
    }


    @Test
    fun `should not fetch one title correctly and tiemout`() {
        mockWebServer.enqueue(
            MockResponse().setBodyDelay(5, TimeUnit.SECONDS).setResponseCode(200).setBody(
                "" +
                        " [ " +
                        " {\n" +
                        "    \"albumId\": 30,\n" +
                        "    \"id\": 2,\n" +
                        "    \"title\": \"reprehenderit est deserunt velit ipsam\",\n" +
                        "    \"url\": \"https://via.placeholder.com/600/771796\",\n" +
                        "    \"thumbnailUrl\": \"https://via.placeholder.com/150/771796\"\n" +
                        "  }" +
                        "]"
            )
        )

        runBlocking {
            val actual = source.fetchTitles().first()

            val expected = Result.failure<List<TitleEntity>>(SocketTimeoutException())

            assertEquals(actual::class, expected::class)
        }
    }

    @Test
    fun `should fetch two titles correctly given 200 response`() {
        mockWebServer.enqueue(
            MockResponse().setResponseCode(200).setBody(
                "" +
                        " [ " +
                        " {\n" +
                        "    \"albumId\": 30,\n" +
                        "    \"id\": 2,\n" +
                        "    \"title\": \"reprehenderit est deserunt velit ipsam\",\n" +
                        "    \"url\": \"https://via.placeholder.com/600/771796\",\n" +
                        "    \"thumbnailUrl\": \"https://via.placeholder.com/150/771796\"\n" +
                        "  }," +
                        " {\n" +
                        "    \"albumId\": 32,\n" +
                        "    \"id\": 4,\n" +
                        "    \"title\": \"ceci est un titre\",\n" +
                        "    \"url\": \"https://leboncoincestsuper.fr\",\n" +
                        "    \"thumbnailUrl\": \"https://leboncoincestsuper.fr\"\n" +
                        "  }" +
                        "]"
            )
        )

        runBlocking {
            var actual = mutableListOf<kotlin.Result<List<TitleEntity>>>()
            source.fetchTitles().collect {
                actual.add(it)
            }


            val expected = listOf(
                kotlin.Result.success(
                    mutableListOf(
                        TitleEntity(
                            albumId = 30,
                            id = 2,
                            title = "reprehenderit est deserunt velit ipsam",
                            url = "https://via.placeholder.com/600/771796",
                            thumbnailUrl = "https://via.placeholder.com/150/771796"
                        ),
                        TitleEntity(
                            albumId = 32,
                            id = 4,
                            title = "ceci est un titre",
                            url = "https://leboncoincestsuper.fr",
                            thumbnailUrl = "https://leboncoincestsuper.fr"
                        )
                    )
                )
            )


            assertEquals(expected.count(), actual.count())
            assertEquals(expected.first(), actual.first())
        }
    }


    @Test
    @Throws
    fun `should return error given 404 response`() {
        mockWebServer.enqueue(
            MockResponse().setResponseCode(404).setBody("")
        )



            runBlocking {
                val actual = source.fetchTitles().first()

                val expected = Result.failure<List<TitleEntity>>(NetworkErrorException())

                assertEquals(expected::class, actual::class)
            }




    }
}