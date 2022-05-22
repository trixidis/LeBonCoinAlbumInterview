package fr.leboncoin.data.source

import fr.leboncoin.data.entity.AlbumEntitiy
import fr.leboncoin.data.entity.TitleEntity
import kotlinx.coroutines.flow.Flow

interface AlbumDataSource {

    fun fetchAlbums() : Flow<List<AlbumEntitiy>>
}