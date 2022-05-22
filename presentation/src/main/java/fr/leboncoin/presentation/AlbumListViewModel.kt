package fr.leboncoin.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.Binds
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.leboncoin.domain.use_cases.UseCaseDisplayTitles
import fr.leboncoin.presentation.model.TitleUiModel
import fr.leboncoin.presentation.ui.TitlesUiState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumListViewModel @Inject constructor(private val useCase: UseCaseDisplayTitles) :
    ViewModel() {

    private val _uiState = MutableStateFlow(TitlesUiState.Success(emptyList()))
    val titlesFlow: StateFlow<TitlesUiState> = _uiState.stateIn(
        initialValue = TitlesUiState.Loading(),
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000)
    )

    init {
        viewModelScope.launch {
            useCase.getTitles().filter {
                it.getOrNull() != null
            }.map {
                it.getOrNull()
            }.filterNotNull()
                .map {
                    it.map {
                        TitleUiModel(it.title)
                    }
                }.collect {
                    _uiState.value = TitlesUiState.Success(
                        it
                    )
                }
        }
    }


}


