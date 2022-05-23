package fr.leboncoin.data.network

import fr.leboncoin.data.entity.TitleEntity
import retrofit2.Response
import retrofit2.http.GET

interface AlbumService {

    @GET("img/shared/technical-test.json")
    suspend fun getAlbums() : Response<List<TitleEntity>>

}