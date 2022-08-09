package com.s1aks.videobase.data.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val adult: Boolean,
    val id: Int,
    val overview: String?,
    val popularity: Number?,
    val backdrop_path: String?,
    val poster_path: String?,
    val release_date: String?,
    val genres: List<Genre>,
    val status: String?,
    val title: String,
    val vote_average: Number?,
    val vote_count: Int?,
    val runtime: Int?
) : Parcelable
