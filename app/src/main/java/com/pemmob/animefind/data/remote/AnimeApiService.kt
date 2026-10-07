package com.pemmob.animefind.data.remote

import com.pemmob.animefind.data.model.AnimeDetailResponse
import com.pemmob.animefind.data.model.AnimeListResponse
import com.pemmob.animefind.data.model.Genre
import com.pemmob.animefind.data.model.GenreListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AnimeApiService {

    @GET("anime")
    suspend fun searchAnime(
        @Query("q") query: String? = null,
        @Query("genres") genres: String? = null,
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 20
    ): AnimeListResponse

    @GET("anime/{id}")
    suspend fun getAnimeDetail(
        @Path("id") id: Int
    ): AnimeDetailResponse

    @GET("genres/anime")
    suspend fun getGenres(): GenreListResponse
}