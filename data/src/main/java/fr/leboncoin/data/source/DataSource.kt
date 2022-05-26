package fr.leboncoin.data.source

import fr.leboncoin.data.database.entity.AlbumEntity
import fr.leboncoin.data.database.entity.AlbumWithTitles
import fr.leboncoin.data.database.entity.TitleEntity
import kotlinx.coroutines.flow.Flow

interface DataSource {

    fun fetchAlbumsWithTitles() : Flow<Result<List<AlbumWithTitles>>>
    fun fetchTitles() : Flow<Result<List<TitleEntity>>>
}