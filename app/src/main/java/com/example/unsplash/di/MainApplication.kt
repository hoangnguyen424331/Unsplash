package com.example.unsplash.di

import android.app.Application
import com.example.unsplash.module.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(
                apiModule,
                storageModule,
                retrofitModule,
                viewModelModule,
                repositoryModule,
                dataSourceModule
            )
        }
    }
}
