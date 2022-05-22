package fr.leboncoin.presentation.ui

import fr.leboncoin.presentation.model.TitleUiModel

sealed class TitlesUiState{
    data class Success(val titles: List<TitleUiModel>): TitlesUiState()
    data class Loading(val data: TitleUiModel? = null): TitlesUiState()
    data class Error(val exception: Throwable): TitlesUiState()
}