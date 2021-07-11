package com.example.unsplash.module

import com.example.unsplash.data.source.remote.RetrofitClient
import com.example.unsplash.utils.Constant
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofitModule = module {

    fun provideGson() = GsonBuilder().create()

    fun provideGsonConverterFactory(factory: Gson) = GsonConverterFactory.create(factory)

    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient
            .Builder()
            .build()
    }

    fun provideClient(retrofit: Retrofit): RetrofitClient {
        return RetrofitClient(retrofit)
    }

    fun provideRetrofit(factory: GsonConverterFactory, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(factory)
            .client(client)
            .build()
    }

    single {
        provideGson()
    }

    single {
        provideGsonConverterFactory(get())
    }

    single {
        provideHttpClient()
    }

    single {
        provideClient(get())
    }

    single {
        provideRetrofit(get(), get())
    }
}
