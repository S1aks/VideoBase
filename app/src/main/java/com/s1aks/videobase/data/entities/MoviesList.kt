package com.s1aks.videobase.data.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MoviesList (
    val results: List<Movie>
): Parcelable