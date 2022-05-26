package fr.leboncoin.presentation

import fr.leboncoin.data.database.entity.AlbumEntity
import fr.leboncoin.data.database.entity.AlbumWithTitles
import fr.leboncoin.data.database.entity.TitleEntity
import fr.leboncoin.domain.use_cases.get_all_albums.GetAlbumsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeGetAlbumsUseCase : GetAlbumsUseCase {
    override suspend fun invoke(): Flow<Result<List<AlbumWithTitles>>> {
        return flow {
            emit(
                Result.success(
                    listOf(
                        AlbumWithTitles(
                            AlbumEntity(1),
                            mutableListOf(
                                TitleEntity(
                                    albumId = 1,
                                    id = 2,
                                    title = "reprehenderit est deserunt velit ipsam",
                                    url = "https://via.placeholder.com/600/771796",
                                    thumbnailUrl = "https://via.placeholder.com/150/771796"
                                )
                            )
                        )
                    )
                )
            )
        }
    }
}