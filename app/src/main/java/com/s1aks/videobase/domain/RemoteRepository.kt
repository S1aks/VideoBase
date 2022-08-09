package com.s1aks.videobase.domain

import com.s1aks.videobase.BuildConfig
import com.s1aks.videobase.data.api.CreditsAPI
import com.s1aks.videobase.data.api.MovieAPI
import com.s1aks.videobase.data.api.MoviesListAPI
import com.s1aks.videobase.data.entities.Credits
import com.s1aks.videobase.data.entities.Movie
import com.s1aks.videobase.data.entities.MoviesList
import com.s1aks.videobase.ui.App.Companion.LOCALE
import com.s1aks.videobase.ui.App.Companion.NEW_LIST_CATEGORY
import retrofit2.Retrofit

class RemoteRepository(builder: Retrofit) : Repository {
    private val movieAPI = builder.create(MovieAPI::class.java)
    private val moviesListAPI = builder.create(MoviesListAPI::class.java)
    private val creditsAPI = builder.create(CreditsAPI::class.java)

    override suspend fun getMovieDetails(id: Int): Movie =
        movieAPI.getMovieAsync(id, BuildConfig.TMDB_API_KEY, LOCALE).await()

    override suspend fun getNewMoviesList(page: Int): MoviesList =
        moviesListAPI
            .getMoviesListAsync(NEW_LIST_CATEGORY, BuildConfig.TMDB_API_KEY, LOCALE, page)
            .await()

    override suspend fun getCredits(id: Int): Credits =
        creditsAPI.getCreditsAsync(id, BuildConfig.TMDB_API_KEY, LOCALE).await()
}

