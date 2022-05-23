package fr.leboncoin.presentation.ui

import fr.leboncoin.presentation.model.AlbumUiModel

sealed class AlbumUiState{
    data class Success(val albums: List<AlbumUiModel>): AlbumUiState()
    data class Loading(val data: AlbumUiModel? = null): AlbumUiState()
    data class Error(val exception: Throwable): AlbumUiState()
}