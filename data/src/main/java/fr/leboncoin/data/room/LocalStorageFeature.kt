package fr.leboncoin.data.room

import fr.leboncoin.data.entity.TitleEntity

interface LocalStorageFeature {

    suspend fun isTitleAlreadyPresentOnStorage(id:Int):Boolean
    suspend fun addTitleToLocalStorage(title : TitleEntity)

}