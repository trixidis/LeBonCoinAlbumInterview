package fr.leboncoin.domain.use_cases

import fr.leboncoin.data.entity.AlbumEntity
import fr.leboncoin.data.entity.AlbumWithTitles
import kotlinx.coroutines.flow.Flow

interface GetAlbumsUseCase {

    suspend operator fun invoke() : Flow<Result<List<AlbumWithTitles>>>

}