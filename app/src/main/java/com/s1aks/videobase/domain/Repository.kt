package com.s1aks.videobase.domain

import com.s1aks.videobase.data.entities.Credits
import com.s1aks.videobase.data.entities.Movie
import com.s1aks.videobase.data.entities.MoviesList

interface Repository {
    suspend fun getMovieDetails(id: Int): Movie
    suspend fun getNewMoviesList(page: Int): MoviesList
    suspend fun getCredits(id: Int): Credits
}
