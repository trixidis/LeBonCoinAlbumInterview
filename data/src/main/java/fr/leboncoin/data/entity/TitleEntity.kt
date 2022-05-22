package fr.leboncoin.data.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(
    tableName = "titles",
    indices = [Index(value = ["albumId"])]
)
data class TitleEntity(
    val albumId: Int,
    @PrimaryKey val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)