package fr.leboncoin.domain.use_cases

import fr.leboncoin.data.entity.AlbumEntitiy
import fr.leboncoin.data.entity.TitleEntity
import kotlinx.coroutines.flow.Flow

interface UseCaseDisplayAlbums {

    suspend fun getAlbums() : Flow<Result<List<AlbumEntitiy>>>

}