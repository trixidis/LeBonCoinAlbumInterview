package fr.leboncoin.data.repository

import fr.leboncoin.data.entity.AlbumEntity
import fr.leboncoin.data.entity.AlbumWithTitles
import fr.leboncoin.data.source.LocalDataSource
import fr.leboncoin.data.source.RemoteDataSource
import fr.leboncoin.data.utils.NetworkMonitor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class AlbumsRepositoryImpl @Inject constructor(
    val remoteDataSource: RemoteDataSource, val localDataSource: LocalDataSource,
    private val monitor: NetworkMonitor
) : AlbumsRepository {

    override suspend fun getAlbums(): Flow<Result<List<AlbumEntity>>> {

        return if (!localDataSource.hasData()) {
            remoteDataSource.fetchTitles()
//                .filter { it.isSuccess }
                .map {
                    it.getOrThrow()
                }.transform {
                    it.asFlow().collect { title ->
                        emit(title)
                    }
                }
                .onEach { title ->
                    localDataSource.addAlbumToLocalStorage(AlbumEntity(title.albumId))
                    localDataSource.addTitleToLocalStorage(title)
                }
                .catch {
                    throw  it
                }
                .flatMapConcat {
                    localDataSource.fetchAlbums().map {
                        Result.success(it)
                    }.catch { emit(Result.failure(it)) }
                        .flowOn(Dispatchers.IO)
                }
        } else {
            localDataSource.fetchAlbums().map {
                Result.success(it)
            }.catch { emit(Result.failure(it)) }
                .flowOn(Dispatchers.IO)
        }


    }

    override suspend fun getAlbumsWithTitles(): Flow<Result<List<AlbumWithTitles>>> {
        return if (!localDataSource.hasData()) {
            remoteDataSource.fetchTitles()
//                .filter { it.isSuccess }
                .map {
                    it.getOrThrow()
                }.transform {
                    it.asFlow().collect { title ->
                        emit(title)
                    }
                }
                .onEach { title ->
                    localDataSource.addAlbumToLocalStorage(AlbumEntity(title.albumId))
                    localDataSource.addTitleToLocalStorage(title)
                }
                .catch {
                    throw  it
                }
                .flatMapConcat {
                    localDataSource.fetchAlbumsWithTitles()
                        .map {
                            Result.success(it)
                        }.catch { emit(Result.failure(it)) }
                        .flowOn(Dispatchers.IO)
                }
        }else{
            localDataSource.fetchAlbumsWithTitles()
                .map {
                    Result.success(it)
                }.catch { emit(Result.failure(it)) }
                .flowOn(Dispatchers.IO)
        }



    }


}