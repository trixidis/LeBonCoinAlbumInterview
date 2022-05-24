package fr.leboncoin.data.source

import fr.leboncoin.data.entity.AlbumEntity
import fr.leboncoin.data.entity.AlbumWithTitles
import kotlinx.coroutines.flow.Flow

interface AlbumDataSource {

    fun fetchAlbums() : Flow<List<AlbumEntity>>
    fun fetchAlbumsWithTitles() : Flow<List<AlbumWithTitles>>
}