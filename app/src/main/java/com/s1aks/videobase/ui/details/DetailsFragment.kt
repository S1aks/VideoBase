package com.s1aks.videobase.ui.details

import android.annotation.SuppressLint
import android.os.Bundle
import coil.load
import com.s1aks.videobase.R
import com.s1aks.videobase.data.entities.Movie
import com.s1aks.videobase.databinding.FragmentDetailsBinding
import com.s1aks.videobase.ui.App
import com.s1aks.videobase.ui.base.BaseFragment
import com.s1aks.videobase.utils.getColorFromIntProgress
import com.s1aks.videobase.utils.toFormattedDateText
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment : BaseFragment<FragmentDetailsBinding>(FragmentDetailsBinding::inflate) {

    private val detailsViewModel: DetailsViewModel by viewModel()
    private val adapter = CastAdapter()
    private var movieId: Int? = null

    override fun readArguments(bundle: Bundle) {
        movieId = bundle.getInt(MOVIE_ID_KEY)
    }

    override fun initView() {
        binding.actorsRv.adapter = adapter
    }

    override fun initObservers() {
        movieId?.let { id ->
            detailsViewModel.movieLiveData.observe(viewLifecycleOwner) {
                renderData(it)
            }
            detailsViewModel.creditsLiveData.observe(viewLifecycleOwner) {
                adapter.submitList(it.cast)
            }
            detailsViewModel.getMovie(id)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun renderData(movie: Movie?) = with(binding) {
        movie?.let { movie ->
            backdrop.load(App.BASE_IMAGE_URL + movie.backdrop_path)
            miniPoster.load(App.BASE_IMAGE_URL + movie.poster_path)
            title.text = movie.title
            date.text =
                getString(R.string.date_string_start) + movie.release_date?.toFormattedDateText
            val minutes = movie.runtime?.mod(60).toString() + getString(R.string.minutes)
            val hours = movie.runtime?.div(60).toString() + getString(R.string.hours)
            time.text = "$hours $minutes"
            genres.text =
                movie.genres.map { it.name }.joinToString().replaceFirstChar { it.uppercaseChar() }
            overview.text = movie.overview
            val numberProgress = movie.vote_average?.toFloat()?.times(10.0)?.toInt() ?: 0
            progress.progress = numberProgress
            progressText.text = numberProgress.toString()
            progress.setIndicatorColor(numberProgress.getColorFromIntProgress)
        }
    }

    companion object {
        const val MOVIE_ID_KEY = "movie_id"
    }
}