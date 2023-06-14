package com.example.imdbwitharch.data.dto

import com.example.imdbwitharch.domain.models.Movie

data class MoviesSearchResponse(val searchType: String,
                                val expression: String,
                                val results: List<Movie>)