package fr.leboncoin.data.repository

import fr.leboncoin.data.entity.AlbumEntitiy
import kotlinx.coroutines.flow.Flow

interface AlbumsRepository {

    suspend fun getAlbums(): Flow<Result<List<AlbumEntitiy>>>
}