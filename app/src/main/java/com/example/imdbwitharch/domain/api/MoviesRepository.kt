package com.example.imdbwitharch.domain.api

import com.example.imdbwitharch.domain.models.Movie

interface MoviesRepository {
    fun searchMovies(expression: String): List<Movie>
}