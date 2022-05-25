package fr.leboncoin.domain.use_cases.get_all_albums

import fr.leboncoin.data.database.entity.AlbumWithTitles
import fr.leboncoin.data.repository.AlbumsRepository
import fr.leboncoin.domain.utils.NetworkMonitor
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAlbumsUseCaseImpl @Inject constructor(private val repository: AlbumsRepository,private val monitor: NetworkMonitor) :
    GetAlbumsUseCase {

    override suspend fun invoke(): Flow<Result<List<AlbumWithTitles>>> {
        return if(monitor.hasNetwork()){
            repository.getAlbumsWithTitlesFromNetwork()
        }else{
            repository.getAlbumsWithTitlesFromLocal()
        }

    }

}