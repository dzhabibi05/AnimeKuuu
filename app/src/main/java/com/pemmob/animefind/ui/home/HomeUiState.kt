package com.pemmob.animefind.ui.home

import com.pemmob.animefind.data.model.AnimeDto
import com.pemmob.animefind.data.model.Genre

data class HomeUiState(
    val query: String = "",
    val selectedGenreId: Int? = null,
    val genres: List<Genre> = emptyList(),
    val animeList: List<AnimeDto> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)