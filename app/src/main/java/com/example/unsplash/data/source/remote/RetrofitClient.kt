package com.example.unsplash.data.source.remote

import retrofit2.Retrofit

class RetrofitClient(private val baseRetrofit: Retrofit) {

    fun <T> create(service: Class<T>): T {
        return baseRetrofit.create(service)
    }
}
