package com.s1aks.videobase.di

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.s1aks.videobase.domain.RemoteRepository
import com.s1aks.videobase.domain.Repository
import com.s1aks.videobase.ui.App.Companion.BASE_URL
import com.s1aks.videobase.ui.details.DetailsViewModel
import com.s1aks.videobase.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().setLenient().create()
                )
            )
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }
    single<Repository> { RemoteRepository(get()) }
    viewModel { MainViewModel(get()) }
    viewModel { DetailsViewModel(get()) }
}