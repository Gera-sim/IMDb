package com.example.imdbwitharch.domain.api

import com.example.imdbwitharch.domain.models.Movie

interface MoviesInteractor {
    fun searchMovies(expression: String, consumer: MoviesConsumer)

    interface MoviesConsumer {
        fun consume(foundMovies: List<Movie>)
    }
}