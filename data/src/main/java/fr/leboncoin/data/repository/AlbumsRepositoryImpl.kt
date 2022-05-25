package fr.leboncoin.data.repository

import fr.leboncoin.data.database.entity.AlbumEntity
import fr.leboncoin.data.database.entity.AlbumWithTitles
import fr.leboncoin.data.database.entity.TitleEntity
import fr.leboncoin.data.source.LocalDataSource
import fr.leboncoin.data.source.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class AlbumsRepositoryImpl @Inject constructor(
    val remoteDataSource: RemoteDataSource, val localDataSource: LocalDataSource
) : AlbumsRepository {

    override suspend fun getAlbumsWithTitlesFromNetwork(): Flow<Result<List<AlbumWithTitles>>> =
        remoteDataSource.fetchAlbumsWithTitles()
            .onEach { albumsWithTitles ->
                albumsWithTitles.getOrThrow().forEach { albumWithTitles ->
                    localDataSource.addAlbumToLocalStorage(albumWithTitles.album)
                    albumWithTitles.titles.forEach { title ->
                        localDataSource.addTitleToLocalStorage(title)
                    }
                }
            }
            .catch { emit(Result.failure(it)) }
            .flowOn(Dispatchers.IO)

    override suspend fun getAlbumsWithTitlesFromLocal(): Flow<Result<List<AlbumWithTitles>>> =
        localDataSource.fetchAlbumsWithTitles()
            .map {
                it
            }.catch { emit(Result.failure(it)) }
            .flowOn(Dispatchers.IO)


}