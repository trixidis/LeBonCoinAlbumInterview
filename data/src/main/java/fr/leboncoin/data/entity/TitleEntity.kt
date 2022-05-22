package fr.leboncoin.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "titles")
data class TitleEntity(val albumId: Int, val id : Int, val title : String, val url : String, val thumbnailUrl : String) {

    @PrimaryKey(autoGenerate = true)
    var internal_id: Long = 0

}