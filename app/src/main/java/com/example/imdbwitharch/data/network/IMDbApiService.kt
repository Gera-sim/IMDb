package com.example.imdbwitharch.data.network

import com.example.imdbwitharch.data.dto.MoviesSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface IMDbApiService {
    @GET("/en/API/SearchMovie/k_r8k6v3s3/{expression}")
    fun searchMovies(@Path("expression") expression: String): Call<MoviesSearchResponse>
}