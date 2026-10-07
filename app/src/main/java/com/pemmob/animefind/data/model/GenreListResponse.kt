package com.pemmob.animefind.data.model

import com.google.gson.annotations.SerializedName

data class GenreListResponse(
    @SerializedName("data")
    val data: List<Genre> = emptyList()
)