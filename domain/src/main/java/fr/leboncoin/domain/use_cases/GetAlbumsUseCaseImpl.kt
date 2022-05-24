package fr.leboncoin.domain.use_cases

import fr.leboncoin.data.entity.AlbumEntity
import fr.leboncoin.data.entity.AlbumWithTitles
import fr.leboncoin.data.repository.AlbumsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import javax.inject.Inject

class GetAlbumsUseCaseImpl @Inject constructor(private val repository: AlbumsRepository) :
    GetAlbumsUseCase {

    override suspend fun invoke(): Flow<Result<List<AlbumWithTitles>>> {
        return repository.getAlbumsWithTitles()

    }

}