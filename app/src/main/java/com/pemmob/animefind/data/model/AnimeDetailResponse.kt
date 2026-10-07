package com.pemmob.animefind.data.model

import com.example.animefind.data.model.AnimeDto
import com.google.gson.annotations.SerializedName

data class AnimeDetailResponse(
    @SerializedName("data")
    val data: AnimeDto
)