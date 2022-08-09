package com.s1aks.videobase.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.s1aks.videobase.data.entities.Credits
import com.s1aks.videobase.data.entities.Movie
import com.s1aks.videobase.domain.Repository
import kotlinx.coroutines.launch

class DetailsViewModel(private val repository: Repository) : ViewModel() {
    var movieLiveData = MutableLiveData<Movie>()
        private set
    var creditsLiveData = MutableLiveData<Credits>()
        private set

    fun getMovie(id: Int) {
        viewModelScope.launch {
            movieLiveData.postValue(repository.getMovieDetails(id))
            creditsLiveData.postValue(repository.getCredits(id))
        }
    }
}