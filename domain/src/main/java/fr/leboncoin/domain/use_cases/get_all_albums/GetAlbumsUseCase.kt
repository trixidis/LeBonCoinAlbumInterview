package fr.leboncoin.domain.use_cases.get_all_albums

import fr.leboncoin.data.database.entity.AlbumWithTitles
import kotlinx.coroutines.flow.Flow

interface GetAlbumsUseCase {

    suspend operator fun invoke() : Flow<Result<List<AlbumWithTitles>>>

}