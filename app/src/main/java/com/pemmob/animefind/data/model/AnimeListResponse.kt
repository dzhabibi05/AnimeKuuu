package com.pemmob.animefind.data.model

import com.example.animefind.data.model.Pagination
import com.google.gson.annotations.SerializedName

data class AnimeListResponse(
    @SerializedName("data")
    val data: List<AnimeDto> = emptyList(),
    @SerializedName("pagination")
    val pagination: Pagination? = null
)