package com.s1aks.videobase.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.s1aks.videobase.data.entities.MoviesList
import com.s1aks.videobase.domain.Repository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel() {
    var liveData = MutableLiveData<MoviesList>(null)
        private set

    fun getFirstMoviesList() {
        viewModelScope.launch {
            if (liveData.value != null) {
                liveData.postValue(liveData.value)
            } else {
                liveData.postValue(repository.getNewMoviesList(1))
            }
        }
    }

    fun getMoviesListNextPage(page: Int) {
        viewModelScope.launch {
            val currentData = liveData.value?.results?.toMutableList()
            val newPage = repository.getNewMoviesList(page)
            currentData?.addAll(newPage.results)
            liveData.postValue(currentData?.let { MoviesList(results = it) })
        }
    }
}