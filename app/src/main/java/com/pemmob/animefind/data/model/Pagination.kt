package com.pemmob.animefind.data.model

import com.google.gson.annotations.SerializedName

data class Pagination(
    @SerializedName("last_visible_page")
    val lastVisiblePage: Int? = null,
    @SerializedName("has_next_page")
    val hasNextPage: Boolean? = null
)