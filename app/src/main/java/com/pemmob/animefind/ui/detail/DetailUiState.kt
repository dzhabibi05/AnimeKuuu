package com.pemmob.animefind.ui.detail

import com.pemmob.animefind.data.model.AnimeDto

data class DetailUiState(
    val isLoading: Boolean = true,
    val anime: AnimeDto? = null,
    val errorMessage: String? = null
)