package fr.leboncoin.data.database

import androidx.room.*
import fr.leboncoin.data.database.entity.TitleEntity

@Dao
interface TitleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTitle(title: TitleEntity)

    @Query("SELECT * FROM titles")
    suspend fun getEveryTitles(): List<TitleEntity>

    @Query("SELECT * FROM titles WHERE id = :titleId")
    suspend fun getTitleWithId(titleId:Int): List<TitleEntity>

    @Delete
    suspend fun deleteTitle(title: TitleEntity):Int

}