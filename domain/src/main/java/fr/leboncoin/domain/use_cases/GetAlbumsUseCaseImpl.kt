package fr.leboncoin.domain.use_cases

import fr.leboncoin.data.entity.AlbumEntitiy
import fr.leboncoin.data.repository.AlbumsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAlbumsUseCaseImpl @Inject constructor(private val repository: AlbumsRepository) :
    GetAlbumsUseCase {

    override suspend fun invoke(): Flow<Result<List<AlbumEntitiy>>> {
        return repository.getAlbums()
    }

}