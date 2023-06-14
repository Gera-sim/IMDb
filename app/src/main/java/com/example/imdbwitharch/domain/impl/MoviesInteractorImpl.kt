package com.example.imdbwitharch.domain.impl


import com.example.imdbwitharch.domain.api.MoviesInteractor
import com.example.imdbwitharch.domain.api.MoviesRepository
import com.example.imdbwitharch.util.Resource
import java.util.concurrent.Executors


class MoviesInteractorImpl(private val repository: MoviesRepository) : MoviesInteractor {

    private val executor = Executors.newCachedThreadPool()

    override fun searchMovies(expression: String, consumer: MoviesInteractor.MoviesConsumer) {
        executor.execute {
            when(val resource = repository.searchMovies(expression)) {
                is Resource.Success -> {
                    resource.data?.let { consumer.consume(it, null) }
                }
                is Resource.Error -> { consumer.consume(null, resource.message) }
            }
        }
    }
}