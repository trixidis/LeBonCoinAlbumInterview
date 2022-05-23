package fr.leboncoin.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import fr.leboncoin.data.entity.AlbumEntitiy

@Dao
interface AlbumDao {

    @Insert
    suspend fun addAlbum(album: AlbumEntitiy)

    @Query("SELECT * FROM albums")
    suspend fun getEveryAlbums(): List<AlbumEntitiy>

    @Query("SELECT * FROM albums WHERE id = :albumId")
    suspend fun getAlbumWithId(albumId:Int): List<AlbumEntitiy>

    @Delete
    suspend fun deleteAlbum(album: AlbumEntitiy):Int

}