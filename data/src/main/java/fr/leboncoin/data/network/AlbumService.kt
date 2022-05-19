package fr.leboncoin.data.network

import fr.leboncoin.data.entity.TitleEntity
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface AlbumService {

    @GET("/img/shared/technical-test.json")
    suspend fun getAlbums() : Flow<TitleEntity>

}