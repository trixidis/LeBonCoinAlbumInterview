package fr.leboncoin.data.repository

import fr.leboncoin.data.entity.AlbumEntity
import fr.leboncoin.data.entity.AlbumWithTitles
import kotlinx.coroutines.flow.Flow

interface AlbumsRepository {

    suspend fun getAlbums(): Flow<Result<List<AlbumEntity>>>
    suspend fun getAlbumsWithTitles() : Flow<Result<List<AlbumWithTitles>>>
}