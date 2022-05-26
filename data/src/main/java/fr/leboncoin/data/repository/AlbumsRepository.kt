package fr.leboncoin.data.repository

import fr.leboncoin.data.database.entity.AlbumEntity
import fr.leboncoin.data.database.entity.AlbumWithTitles
import kotlinx.coroutines.flow.Flow

interface AlbumsRepository {

    suspend fun getAlbumsWithTitlesFromLocal() : Flow<Result<List<AlbumWithTitles>>>
    suspend fun getAlbumsWithTitlesFromNetwork() : Flow<Result<List<AlbumWithTitles>>>
}