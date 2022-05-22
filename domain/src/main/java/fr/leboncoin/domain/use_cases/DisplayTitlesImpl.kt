package fr.leboncoin.domain.use_cases

import fr.leboncoin.data.entity.TitleEntity
import fr.leboncoin.data.repository.AlbumsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DisplayTitlesImpl @Inject constructor(private val repository: AlbumsRepository) :
    UseCaseDisplayTitles {

    override suspend fun getTitles(): Flow<Result<List<TitleEntity>>> {
        return repository.getTitles()
    }

}