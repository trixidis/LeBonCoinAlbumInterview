package fr.leboncoin.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.leboncoin.domain.use_cases.UseCaseDisplayTitles
import fr.leboncoin.presentation.model.TitleUiModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumListViewModel @Inject constructor(val useCase : UseCaseDisplayTitles): ViewModel() {

    private val _uiState = MutableStateFlow(TitlesUiState.Success(emptyList()))
    val titlesFlow :StateFlow<TitlesUiState> =_uiState

    init {
        viewModelScope.launch {
            useCase.getTitles().filter {
                it.getOrNull() != null
            }.map {
                TitleUiModel(it.getOrNull()!!.title)
            }.collect{
                _uiState.value = TitlesUiState.Success(
                    listOf(it)
                )
            }
        }

    }



}

sealed class TitlesUiState{
    data class Success(val news: List<TitleUiModel>): TitlesUiState()
    data class Error(val exception: Throwable): TitlesUiState()
}
