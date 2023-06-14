package com.example.imdbwitharch.presentation.movies

import com.example.imdbwitharch.ui.models.MoviesState

interface MoviesView {
    // Методы, меняющие внешний вид экрана
    fun render(state: MoviesState)
    // Методы «одноразовых событий»
    fun showToast(additionalMessage: String)

}