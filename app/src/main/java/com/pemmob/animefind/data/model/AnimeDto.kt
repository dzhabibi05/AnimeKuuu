package com.pemmob.animefind.data.model

import com.pemmob.animefind.data.model.Genre
import com.google.gson.annotations.SerializedName

data class AnimeDto(
    @SerializedName("mal_id")
    val malId: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("type")
    val type: String? = null,
    @SerializedName("score")
    val score: Double? = null,
    @SerializedName("episodes")
    val episodes: Int? = null,
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("synopsis")
    val synopsis: String? = null,
    @SerializedName("genres")
    val genres: List<Genre>? = null,
    @SerializedName("images")
    val images: ImagesDto? = null
) {
    // Helper property untuk UI agar selalu mendapatkan teks yang aman dari null
    val displayScore: String
        get() = score?.let { "★ %.1f".format(it) } ?: "★ N/A"

    val displayEpisodes: String
        get() = episodes?.let { "$it Ep" } ?: "Episode N/A"

    val displayType: String
        get() = type ?: "Unknown"

    val displayStatus: String
        get() = status ?: "-"

    val displaySynopsis: String
        get() = synopsis ?: "Sinopsis tidak tersedia."

    val imageUrl: String
        get() = images?.jpg?.imageUrl.orEmpty()
}

data class ImagesDto(
    @SerializedName("jpg")
    val jpg: ImageUrlDto? = null
)

data class ImageUrlDto(
    @SerializedName("image_url")
    val imageUrl: String? = null
)