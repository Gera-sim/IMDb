package com.example.imdbwitharch.presentation
import com.example.imdbwitharch.ui.poster.PosterView
class PosterPresenter(private val view: PosterView, private val imageUrl: String) {
    fun onCreate() {view.setupPosterImage(imageUrl)}}