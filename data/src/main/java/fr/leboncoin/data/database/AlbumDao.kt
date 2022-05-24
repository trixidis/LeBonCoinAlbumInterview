package fr.leboncoin.data.database

import androidx.room.*
import fr.leboncoin.data.entity.AlbumWithTitles
import fr.leboncoin.data.entity.AlbumEntity

@Dao
interface AlbumDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAlbum(album: AlbumEntity)

    @Query("SELECT * FROM albums")
    suspend fun getEveryAlbums(): List<AlbumEntity>

    @Transaction
    @Query("SELECT * FROM albums")
    fun getAlbumsWithTitles(): List<AlbumWithTitles>

    @Query("SELECT * FROM albums WHERE id = :albumId")
    suspend fun getAlbumWithId(albumId:Int): List<AlbumEntity>

    @Delete
    suspend fun deleteAlbum(album: AlbumEntity):Int

}