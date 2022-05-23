package fr.leboncoin.domain.use_cases

import fr.leboncoin.data.entity.AlbumEntitiy
import fr.leboncoin.data.entity.TitleEntity
import kotlinx.coroutines.flow.Flow

interface GetAlbumsUseCase {

    suspend operator fun invoke() : Flow<Result<List<AlbumEntitiy>>>

}