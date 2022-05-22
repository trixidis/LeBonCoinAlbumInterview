package fr.leboncoin.domain.use_cases

import fr.leboncoin.data.entity.TitleEntity
import kotlinx.coroutines.flow.Flow

interface UseCaseDisplayTitles {

    fun getTitles() : Flow<Result<TitleEntity>>


}