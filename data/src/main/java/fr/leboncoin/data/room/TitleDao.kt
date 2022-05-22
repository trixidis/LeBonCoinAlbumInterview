package fr.leboncoin.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import fr.leboncoin.data.entity.TitleEntity

@Dao
interface TitleDao {

    @Insert
    suspend fun addTitle(title: TitleEntity)

    @Query("SELECT * FROM Titles")
    suspend fun getEveryTitles(): List<TitleEntity>

    @Query("SELECT * FROM Titles WHERE id = :titleId")
    suspend fun getTitleWithId(titleId:Int): List<TitleEntity>

    @Delete
    suspend fun deleteTitle(title: TitleEntity):Int

}