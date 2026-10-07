package com.pemmob.animefind.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pemmob.animefind.data.repository.AnimeRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(FlowPreview::class)
class HomeViewModel(
    private val repository: AnimeRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val _searchQueryFlow = MutableStateFlow("")

    init {
        loadGenres()
        observeSearchQuery()
        fetchAnimeList()
    }

    private fun loadGenres() {
        viewModelScope.launch {
            repository.getGenres().onSuccess { genreList ->
                _uiState.update { it.copy(genres = genreList) }
            }
        }
    }

    private fun observeSearchQuery() {
        viewModelScope.launch {
            _searchQueryFlow
                .debounce(500L)
                .distinctUntilChanged()
                .collect { query ->
                    fetchAnimeList(query = query, genreId = _uiState.value.selectedGenreId)
                }
        }
    }

    fun onQueryChange(newQuery: String) {
        _uiState.update { it.copy(query = newQuery) }
        _searchQueryFlow.value = newQuery
    }

    fun onGenreSelected(genreId: Int?) {
        val newGenreId = if (_uiState.value.selectedGenreId == genreId) null else genreId
        _uiState.update { it.copy(selectedGenreId = newGenreId) }
        fetchAnimeList(query = _uiState.value.query, genreId = newGenreId)
    }

    fun retry() {
        fetchAnimeList(query = _uiState.value.query, genreId = _uiState.value.selectedGenreId)
    }

    private fun fetchAnimeList(
        query: String = _uiState.value.query,
        genreId: Int? = _uiState.value.selectedGenreId
    ) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            repository.searchAnime(query = query, genreId = genreId)
                .onSuccess { list ->
                    _uiState.update {
                        it.copy(animeList = list, isLoading = false, errorMessage = null)
                    }
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(isLoading = false, errorMessage = error.message)
                    }
                }
        }
    }
}