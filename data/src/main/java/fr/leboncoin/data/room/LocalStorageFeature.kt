package fr.leboncoin.data.room

import fr.leboncoin.data.entity.AlbumEntitiy
import fr.leboncoin.data.entity.TitleEntity

interface LocalStorageFeature {

    suspend fun isAlbumAlreadyPresentOnStorage(id:Int):Boolean
    suspend fun isTitleAlreadyPresentOnStorage(id:Int):Boolean
    suspend fun addTitleToLocalStorage(title : TitleEntity)
    suspend fun addAlbumToLocalStorage(album : AlbumEntitiy)

}