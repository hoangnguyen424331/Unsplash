package com.example.unsplash.module

import com.example.unsplash.data.source.remote.APIService
import com.example.unsplash.data.source.remote.RetrofitClient
import org.koin.dsl.module

val apiModule = module {

    fun provideAPIService(retrofit: RetrofitClient) = retrofit.create(APIService::class.java)

    single { provideAPIService(get()) }
}
