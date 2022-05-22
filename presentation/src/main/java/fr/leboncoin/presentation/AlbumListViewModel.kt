package fr.leboncoin.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.leboncoin.domain.use_cases.UseCaseDisplayAlbums
import fr.leboncoin.presentation.model.AlbumUiModel
import fr.leboncoin.presentation.ui.AlbumUiState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumListViewModel @Inject constructor(private val useCase: UseCaseDisplayAlbums) :
    ViewModel() {

    private val _uiState = MutableStateFlow(AlbumUiState.Success(emptyList()))
    val albumsFlow: StateFlow<AlbumUiState> = _uiState.stateIn(
        initialValue = AlbumUiState.Loading(),
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000)
    )

    init {
        viewModelScope.launch {
            useCase.getAlbums().filter {
                it.getOrNull() != null
            }.map {
                it.getOrNull()
            }.filterNotNull()
                .map {
                    it.map {
                        AlbumUiModel(it.id)
                    }
                }.collect {
                    _uiState.value = AlbumUiState.Success(
                        it
                    )
                }
        }
    }


}


