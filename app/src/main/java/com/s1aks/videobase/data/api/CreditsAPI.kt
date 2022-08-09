package com.s1aks.videobase.data.api

import com.s1aks.videobase.data.entities.Credits
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CreditsAPI {
    @GET("3/movie/{movie_id}/credits")
    fun getCreditsAsync(
        @Path("movie_id") movieId: Int,
        @Query("api_key") key: String,
        @Query("language") lang: String
    ): Deferred<Credits>
}