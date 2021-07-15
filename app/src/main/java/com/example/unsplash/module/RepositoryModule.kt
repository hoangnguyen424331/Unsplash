package com.example.unsplash.module

import com.example.unsplash.data.repository.PhotoRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { PhotoRepository(get()) }
}
