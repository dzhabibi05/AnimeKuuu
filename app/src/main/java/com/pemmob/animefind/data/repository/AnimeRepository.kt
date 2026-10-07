package com.pemmob.animefind.data.repository

import com.pemmob.animefind.data.model.AnimeDto
import com.pemmob.animefind.data.model.Genre
import com.pemmob.animefind.data.remote.AnimeApiService
import retrofit2.HttpException
import java.io.IOException

class AnimeRepository(
    private val apiService: AnimeApiService
) {

    suspend fun searchAnime(query: String?, genreId: Int?): Result<List<AnimeDto>> {
        return runCatching {
            val genreParam = genreId?.toString()
            val queryParam = query?.ifBlank { null }
            val response = apiService.searchAnime(query = queryParam, genres = genreParam)
            response.data
        }.mapErrorToUserFriendly()
    }

    suspend fun getAnimeDetail(malId: Int): Result<AnimeDto> {
        return runCatching {
            val response = apiService.getAnimeDetail(malId)
            response.data
        }.mapErrorToUserFriendly()
    }

    suspend fun getGenres(): Result<List<Genre>> {
        return runCatching {
            apiService.getGenres()
        }.mapErrorToUserFriendly()
    }

    private fun <T> Result<T>.mapErrorToUserFriendly(): Result<T> {
        return this.onFailure { exception ->
            val errorMessage = when (exception) {
                is IOException -> "Koneksi internet terputus. Harap periksa jaringan Anda."
                is HttpException -> when (exception.code()) {
                    429 -> "Terlalu banyak permintaan. Silakan tunggu beberapa saat."
                    404 -> "Data anime tidak ditemukan."
                    in 500..599 -> "Server Tenrai sedang bermasalah. Coba lagi nanti."
                    else -> "Gagal mengambil data dari server (${exception.code()})."
                }
                else -> exception.localizedMessage ?: "Telah terjadi kesalahan tidak terduga."
            }
            return Result.failure(Exception(errorMessage))
        }
    }
}