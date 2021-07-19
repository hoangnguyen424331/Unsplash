package com.example.unsplash.module

import com.example.unsplash.data.source.PhotoDataSource
import com.example.unsplash.data.source.local.PhotoLocalDataSource
import com.example.unsplash.data.source.remote.PhotoRemoteDataSource
import org.koin.dsl.bind
import org.koin.dsl.module

val dataSourceModule = module {
    single { PhotoRemoteDataSource(get()) } bind (PhotoDataSource.Remote::class)
    single { PhotoLocalDataSource(get()) } bind (PhotoDataSource.Local::class)
}
