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

    @Query("SELECT * FROM Titles LIMIT 20")
    suspend fun getLast20Titles(): List<TitleEntity>

    @Delete
    suspend fun deleteTitle(title: TitleEntity):Int

}