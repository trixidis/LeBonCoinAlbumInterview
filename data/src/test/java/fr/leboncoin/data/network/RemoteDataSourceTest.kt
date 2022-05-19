package fr.leboncoin.data.network

import fr.leboncoin.data.entity.TitleEntity
import fr.leboncoin.data.serializer.defaultConverter
import fr.leboncoin.data.source.FakeDataSource
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
import org.junit.Test
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

@ExperimentalCoroutinesApi
@ExperimentalSerializationApi
class MoviesNetworkDataSourceTest {
    private val mockWebServer = MockWebServer()

    private val client = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.SECONDS)
        .readTimeout(1, TimeUnit.SECONDS)
        .writeTimeout(1, TimeUnit.SECONDS)
        .build()

    private val api = Retrofit.Builder()
        .baseUrl(mockWebServer.url("/"))
        .client(client)
        .addConverterFactory(defaultConverter(isLenient = true))
        .build()
        .create(AlbumService::class.java)

    private val sut = FakeDataSource()

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `should fetch movies correctly given 200 response`() {
        mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody("" +
                "  {\n" +
                "    \"albumId\": 1,\n" +
                "    \"id\": 2,\n" +
                "    \"title\": \"reprehenderit est deserunt velit ipsam\",\n" +
                "    \"url\": \"https://via.placeholder.com/600/771796\",\n" +
                "    \"thumbnailUrl\": \"https://via.placeholder.com/150/771796\"\n" +
                "  },"))

        runBlocking {
            val actual = sut.fetchTitles().first()


            val expected =
                TitleEntity(
                       albumId= 1,
                       id= 2,
                       title= "reprehenderit est deserunt velit ipsam",
                       url= "https://via.placeholder.com/600/771796",
                       thumbnailUrl= "https://via.placeholder.com/150/771796"
                )


            Assert.assertEquals(expected, actual)
        }
    }
}