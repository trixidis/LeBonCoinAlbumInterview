package fr.leboncoin.data.database.entity

import androidx.room.Embedded
import androidx.room.Relation


data class AlbumWithTitles(

    @Embedded
     val album: AlbumEntity,

    @Relation(
        parentColumn = "id", entityColumn = "albumId"
    )
    val titles: MutableList<TitleEntity>
)