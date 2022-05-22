package fr.leboncoin.data.di

import android.app.Application
import android.content.Context
import androidx.test.core.app.ApplicationProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import fr.leboncoin.data.utils.Config
import fr.leboncoin.data.network.AlbumService
import fr.leboncoin.data.repository.AlbumsRepository
import fr.leboncoin.data.repository.AlbumsRepositoryImpl
import fr.leboncoin.data.source.LocalDataSource
import fr.leboncoin.data.source.RemoteDataSource
import fr.leboncoin.data.utils.NetworkMonitor
import fr.leboncoin.data.utils.NetworkMonitorImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val baseUrl = Config.BASE_URL

    @Provides
    fun provideHTTPLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return interceptor
    }

    @Provides
    fun provideAlbumService(okHttpClient: OkHttpClient): AlbumService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(AlbumService::class.java)
    }

    @Provides
    fun provideHttpClient(): OkHttpClient {
        return  OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

    }

    @Provides
    fun provideLocalDataSource(@ApplicationContext appContext: Context):LocalDataSource {
        return LocalDataSource(appContext)
    }

    @Provides
    fun provideRemoteDataSource():RemoteDataSource {
        return RemoteDataSource(provideAlbumService(provideHttpClient()))
    }

    @Provides
    fun provideNetworkMonitor(@ApplicationContext appContext: Context):NetworkMonitor {
        return NetworkMonitorImpl(appContext)
    }

    @Provides
    fun provideAlbumsRepository(@ApplicationContext appContext: Context):AlbumsRepository {
        return AlbumsRepositoryImpl(provideRemoteDataSource(), provideLocalDataSource(appContext),
            provideNetworkMonitor(appContext))
    }


}