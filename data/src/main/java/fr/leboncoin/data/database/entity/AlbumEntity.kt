package fr.leboncoin.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable


@Serializable
@Entity(
    tableName = "albums",
)
data class AlbumEntity(@PrimaryKey val id: Int) {
}