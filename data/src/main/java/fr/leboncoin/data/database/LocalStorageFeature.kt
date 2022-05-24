package fr.leboncoin.data.database

import fr.leboncoin.data.entity.AlbumEntity
import fr.leboncoin.data.entity.AlbumWithTitles
import fr.leboncoin.data.entity.TitleEntity
import kotlinx.coroutines.flow.Flow

interface LocalStorageFeature {

    suspend fun isAlbumAlreadyPresentOnStorage(id:Int):Boolean
    suspend fun isTitleAlreadyPresentOnStorage(id:Int):Boolean
    suspend fun addTitleToLocalStorage(title : TitleEntity)
    suspend fun addAlbumToLocalStorage(album : AlbumEntity)
    suspend fun hasData():Boolean

}