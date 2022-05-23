package fr.leboncoin.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.leboncoin.domain.use_cases.GetAlbumsUseCase
import fr.leboncoin.presentation.model.AlbumUiModel
import fr.leboncoin.presentation.ui.AlbumUiState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumListViewModel @Inject constructor(private val getAlbumsUseCase: GetAlbumsUseCase) :
    ViewModel() {

    private val _uiState = MutableStateFlow<AlbumUiState>(AlbumUiState.Loading())
    val albumsFlow: StateFlow<AlbumUiState> = _uiState.stateIn(
        initialValue = AlbumUiState.Loading(),
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000)
    )


    init {
        viewModelScope.launch {
            getAlbumsUseCase().filter {
                it.isSuccess
            }.map {
                it.getOrNull()
            }.filterNotNull()
                .map { listLAlbumsEntity ->
                    listLAlbumsEntity.map {
                        AlbumUiModel(it.id)
                    }
                }.map {
                    _uiState.value = AlbumUiState.Success(
                        it
                    )
                }.onStart {
                    _uiState.value = AlbumUiState.Loading()
                }.collectLatest {

                }
        }

    }
}


