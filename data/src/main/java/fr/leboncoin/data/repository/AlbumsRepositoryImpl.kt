package fr.leboncoin.data.repository

import fr.leboncoin.data.entity.TitleEntity
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

    override suspend fun getTitles(): Flow<Result<TitleEntity>> {
        return if (monitor.hasNetwork()) {
            remoteDataSource.fetchTitles()
                .onEach { title ->
                    if (!localDataSource.isTitleAlreadyPresentOnStorage(title.id)) {
                        localDataSource.addTitleToLocalStorage(title)
                    }
                }.map {
                    Result.success(it)
                }.catch { emit(Result.failure(it)) }
                .flowOn(Dispatchers.IO)
        } else {
            remoteDataSource.fetchTitles().map {
                Result.success(it)
            }.catch { emit(Result.failure(it)) }
                .flowOn(Dispatchers.IO)
        }
    }


}