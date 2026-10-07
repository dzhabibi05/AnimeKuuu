package com.pemmob.animefind.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.pemmob.animefind.data.model.Genre

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenreFilterRow(
    genres: List<Genre>,
    selectedGenreId: Int?,
    onGenreSelected: (Int?) -> Unit
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(genres, key = { it.malId }) { genre ->
            val isSelected = genre.malId == selectedGenreId
            FilterChip(
                selected = isSelected,
                onClick = { onGenreSelected(genre.malId) },
                label = { Text(genre.name) }
            )
        }
    }
}