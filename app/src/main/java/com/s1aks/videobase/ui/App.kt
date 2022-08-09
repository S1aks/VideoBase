package com.s1aks.videobase.ui

import android.app.Application
import com.s1aks.videobase.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(appModule)
        }
    }

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/"
        const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/original"
        const val LOCALE = "ru"
        const val NEW_LIST_CATEGORY = "now_playing"
        const val TOP_LIST_CATEGORY = "top_rated"
    }
}