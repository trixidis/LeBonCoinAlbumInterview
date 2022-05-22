package fr.leboncoin.data.repository

import fr.leboncoin.data.entity.TitleEntity
import kotlinx.coroutines.flow.Flow

interface AlbumsRepository {

    suspend fun getTitles(): Flow<Result<List<TitleEntity>>>
}