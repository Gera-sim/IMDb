package com.example.imdbwitharch.domain.impl

import com.example.imdbwitharch.data.LocalStorage
import com.example.imdbwitharch.data.NetworkClient
import com.example.imdbwitharch.data.dto.MoviesSearchRequest
import com.example.imdbwitharch.data.dto.MoviesSearchResponse
import com.example.imdbwitharch.domain.api.MoviesRepository
import com.example.imdbwitharch.domain.models.Movie
import com.example.imdbwitharch.util.Resource

class MoviesRepositoryImpl(
    private val networkClient: NetworkClient,
    private val localStorage: LocalStorage,
) : MoviesRepository {

    override fun searchMovies(expression: String): Resource<List<Movie>> {
        val response = networkClient.doRequest(MoviesSearchRequest(expression))
        return when (response.resultCode) {
            -1 -> {
                Resource.Error("Проверьте подключение к интернету")
            }
            200 -> {
                val stored = localStorage.getSavedFavorites()
                Resource.Success((response as MoviesSearchResponse).results.map {
                    Movie(
                        id = it.id,
                        resultType = it.resultType,
                        image = it.image,
                        title = it.title,
                        description = it.description,
                        inFavorite = stored.contains(it.id),
                    )})
            }
            else -> {
                Resource.Error("Ошибка сервера ${response.resultCode}")
            }
        }
    }

    override fun addMovieToFavorites(movie: Movie) {
        localStorage.addToFavorites(movie.id)
    }

    override fun removeMovieFromFavorites(movie: Movie) {
        localStorage.removeFromFavorites(movie.id)
    }
}