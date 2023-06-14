package com.example.imdbwitharch

import com.example.imdbwitharch.data.MoviesRepositoryImpl
import com.example.imdbwitharch.data.network.RetrofitNetworkClient
import com.example.imdbwitharch.domain.api.MoviesInteractor
import com.example.imdbwitharch.domain.api.MoviesRepository
import com.example.imdbwitharch.domain.impl.MoviesInteractorImpl

object Creator {
    private fun getMoviesRepository(): MoviesRepository {
        return MoviesRepositoryImpl(RetrofitNetworkClient())
    }

    fun provideMoviesInteractor(): MoviesInteractor {
        return MoviesInteractorImpl(getMoviesRepository())
    }
}