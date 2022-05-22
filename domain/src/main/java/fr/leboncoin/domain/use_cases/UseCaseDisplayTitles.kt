package fr.leboncoin.domain.use_cases

import fr.leboncoin.data.entity.TitleEntity
import kotlinx.coroutines.flow.Flow

interface UseCaseDisplayTitles {

    suspend fun getTitles() : Flow<Result<List<TitleEntity>>>

}