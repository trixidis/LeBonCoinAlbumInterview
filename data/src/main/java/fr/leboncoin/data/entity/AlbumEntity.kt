package fr.leboncoin.data.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable


@Serializable
@Entity(
    tableName = "albums",
)
data class AlbumEntity(@PrimaryKey val id: Int) {
}