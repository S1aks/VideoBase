package com.s1aks.videobase.data.api

import com.s1aks.videobase.data.entities.MoviesList
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesListAPI {
    @GET("3/movie/{list_category}")
    fun getMoviesListAsync(
        @Path("list_category") listCategory: String,
        @Query("api_key") key: String,
        @Query("language") lang: String,
        @Query("page") page: Int,
    ): Deferred<MoviesList>
}