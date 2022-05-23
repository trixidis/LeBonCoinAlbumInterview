package fr.leboncoin.data.source

import fr.leboncoin.data.entity.TitleEntity
import kotlinx.coroutines.flow.Flow

interface TitleDataSource {

    suspend fun fetchTitles() : Flow<Result<List<TitleEntity>>>

}