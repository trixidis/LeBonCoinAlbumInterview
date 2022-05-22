package fr.leboncoin.domain.use_cases

import fr.leboncoin.data.entity.AlbumEntitiy
import fr.leboncoin.data.repository.AlbumsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DisplayAlbumsImpl @Inject constructor(private val repository: AlbumsRepository) :
    UseCaseDisplayAlbums {

    override suspend fun getAlbums(): Flow<Result<List<AlbumEntitiy>>> {
        return repository.getAlbums()
    }

}