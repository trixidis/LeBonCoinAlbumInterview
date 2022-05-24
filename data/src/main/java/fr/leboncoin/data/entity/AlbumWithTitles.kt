package fr.leboncoin.data.entity

import androidx.room.Embedded
import androidx.room.Relation


data class AlbumAndTitles(

    @Embedded
     val album: AlbumEntitiy,

    @Relation(
        parentColumn = "id", entityColumn = "albumId"
    )
    val titles: List<TitleEntity>
)