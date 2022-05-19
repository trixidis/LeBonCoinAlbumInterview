package fr.leboncoin.data.source

import fr.leboncoin.data.entity.TitleEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeDataSource : TitleDataSource {
    override suspend fun fetchTitles(): Flow<TitleEntity> {
        return flow<TitleEntity> {
            emit(
                TitleEntity(
                    albumId= 1,
                    id= 2,
                    title= "reprehenderit est deserunt velit ipsam",
                    url= "https://via.placeholder.com/600/771796",
                    thumbnailUrl= "https://via.placeholder.com/150/771796"
            )
            )
        }
    }
}