package com.example.unsplash.di

import android.app.Application
import com.example.unsplash.module.apiModule
import com.example.unsplash.module.repositoryModule
import com.example.unsplash.module.retrofitModule
import com.example.unsplash.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(
                apiModule,
                retrofitModule,
                viewModelModule,
                repositoryModule
            )
        }
    }
}
