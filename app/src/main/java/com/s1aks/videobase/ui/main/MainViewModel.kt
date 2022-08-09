package com.s1aks.videobase.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.s1aks.videobase.data.entities.MoviesList
import com.s1aks.videobase.domain.Repository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel() {
    var liveData = MutableLiveData<MoviesList>()
        private set

    fun getMoviesList(page: Int) {
        viewModelScope.launch {
            liveData.postValue(repository.getNewMoviesList(page))
        }
    }
}