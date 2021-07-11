package com.example.unsplash.module

import com.example.unsplash.data.source.remote.CollectionAPI
import com.example.unsplash.data.source.remote.RetrofitClient
import com.example.unsplash.utils.Constant.BASE_URL
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val apiModule = module {
    fun provideCollectionApi(retrofit: RetrofitClient) = retrofit.create(CollectionAPI::class.java)
    single {
        provideCollectionApi(get())
    }
}
