package com.example.imdbwitharch.util

import android.app.Activity
import android.content.Context
import com.example.imdbwitharch.PosterController
import com.example.imdbwitharch.data.MoviesRepositoryImpl
import com.example.imdbwitharch.data.network.RetrofitNetworkClient
import com.example.imdbwitharch.domain.api.MoviesInteractor
import com.example.imdbwitharch.domain.api.MoviesRepository
import com.example.imdbwitharch.domain.impl.MoviesInteractorImpl
import com.example.imdbwitharch.presentation.MoviesSearchController
import com.example.imdbwitharch.ui.movies.MoviesAdapter

object Creator {
    private fun getMoviesRepository(context: Context): MoviesRepository {
        return MoviesRepositoryImpl(RetrofitNetworkClient(context))
    }

    fun provideMoviesInteractor(context: Context): MoviesInteractor {
        return MoviesInteractorImpl(getMoviesRepository(context))
    }

    fun provideMoviesSearchController(activity: Activity, adapter: MoviesAdapter): MoviesSearchController {
        return MoviesSearchController(activity, adapter)
    }

    fun providePosterController(activity: Activity): PosterController {
        return PosterController(activity)
    }

}