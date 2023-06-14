package com.example.imdbwitharch.domain.api

import com.example.imdbwitharch.domain.models.Movie
import com.example.imdbwitharch.util.Resource

interface MoviesRepository {
    fun searchMovies(expression: String): Resource<List<Movie>>
}