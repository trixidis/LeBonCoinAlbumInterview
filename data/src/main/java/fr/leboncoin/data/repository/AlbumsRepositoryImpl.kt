package fr.leboncoin.data.repository

import fr.leboncoin.data.entity.AlbumEntitiy
import fr.leboncoin.data.entity.TitleEntity
import fr.leboncoin.data.source.LocalDataSource
import fr.leboncoin.data.source.RemoteDataSource
import fr.leboncoin.data.utils.NetworkMonitor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class AlbumsRepositoryImpl @Inject constructor(
    val remoteDataSource: RemoteDataSource, val localDataSource: LocalDataSource,
    private val monitor: NetworkMonitor
) : AlbumsRepository {

    override suspend fun getAlbums(): Flow<Result<List<AlbumEntitiy>>> {

        remoteDataSource.fetchTitles()
            .forEach { title ->
                if (!localDataSource.isAlbumAlreadyPresentOnStorage(title.albumId)) {
                    localDataSource.addAlbumToLocalStorage(AlbumEntitiy(title.albumId))
                }
                if (!localDataSource.isTitleAlreadyPresentOnStorage(title.id)) {
                    localDataSource.addTitleToLocalStorage(title)
                }
            }


        return localDataSource.fetchAlbums().map {
            Result.success(it)
        }.catch { emit(Result.failure(it)) }
            .flowOn(Dispatchers.IO)

    }


}