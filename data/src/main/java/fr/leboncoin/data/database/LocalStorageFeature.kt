package fr.leboncoin.data.database

import fr.leboncoin.data.database.entity.AlbumEntity
import fr.leboncoin.data.database.entity.TitleEntity

interface LocalStorageFeature {

    suspend fun addTitleToLocalStorage(title : TitleEntity)
    suspend fun addAlbumToLocalStorage(album : AlbumEntity)
    suspend fun hasData():Boolean

}