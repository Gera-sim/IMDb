package com.example.imdbwitharch.data

import com.example.imdbwitharch.data.dto.MoviesSearchRequest
import com.example.imdbwitharch.data.dto.MoviesSearchResponse
import com.example.imdbwitharch.domain.api.MoviesRepository
import com.example.imdbwitharch.domain.models.Movie
import com.example.imdbwitharch.util.Resource

class MoviesRepositoryImpl(private val networkClient: NetworkClient) : MoviesRepository {

    override fun searchMovies(expression: String): Resource<List<Movie>> {
        val response = networkClient.doRequest(MoviesSearchRequest(expression))
        return when (response.resultCode) {
            -1 -> {
                Resource.Error("Проверьте подключение к интернету")
            }
            200 -> {
                Resource.Success((response as MoviesSearchResponse).results.map {
                    Movie(it.id, it.resultType, it.image, it.title, it.description)})
            }
            else -> {
                Resource.Error("Ошибка сервера")
            }
        }
    }
}