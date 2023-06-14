package com.example.imdbwitharch.data

import com.example.imdbwitharch.data.dto.Response

interface NetworkClient {
    fun doRequest(dto: Any): Response
}