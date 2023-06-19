package com.example.imdbwitharch.util

import android.content.Context
import com.example.imdbwitharch.data.LocalStorage
import com.example.imdbwitharch.presentation.poster.PosterPresenter
import com.example.imdbwitharch.domain.impl.MoviesRepositoryImpl
import com.example.imdbwitharch.data.network.RetrofitNetworkClient
import com.example.imdbwitharch.domain.api.MoviesInteractor
import com.example.imdbwitharch.domain.api.MoviesRepository
import com.example.imdbwitharch.domain.impl.MoviesInteractorImpl
import com.example.imdbwitharch.presentation.poster.PosterView

object Creator {
       fun provideMoviesInteractor(context: Context): MoviesInteractor {
        return MoviesInteractorImpl(getMoviesRepository(context))
    }

    fun providePosterPresenter(posterView: PosterView, imageUrl: String): PosterPresenter {
        return PosterPresenter(posterView, imageUrl)
    }

    private fun getMoviesRepository(context: Context): MoviesRepository {
        return MoviesRepositoryImpl(
            RetrofitNetworkClient(context),
            LocalStorage(context.getSharedPreferences("local_storage", Context.MODE_PRIVATE)),
        )
    }

}