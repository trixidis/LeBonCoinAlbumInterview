package fr.leboncoin.data.source

import fr.leboncoin.data.entity.TitleEntity
import kotlinx.coroutines.flow.Flow

class LocalDataSource(): TitleDataSource {
    override suspend fun fetchTitles(): Flow<TitleEntity> {
        TODO("Not yet implemented")
    }
}