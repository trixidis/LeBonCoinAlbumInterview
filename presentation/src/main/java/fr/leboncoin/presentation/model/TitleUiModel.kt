package fr.leboncoin.presentation.model

import androidx.versionedparcelable.ParcelField
import kotlinx.serialization.Serializable

data class TitleUiModel(
    val name: String,
    val url: String,
    val thumbnailUrl: String,
    val id:Int
)