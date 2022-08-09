package com.s1aks.videobase.ui.main

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.s1aks.videobase.R
import com.s1aks.videobase.databinding.FragmentMainBinding
import com.s1aks.videobase.ui.base.BaseFragment
import com.s1aks.videobase.ui.details.DetailsFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate),
    OnItemViewClickListener {
    private val mainViewModel: MainViewModel by viewModel()
    private val adapter = MainAdapter(this)
    private var page = 1
    private var isLastPage: Boolean = false
    private var isLoading: Boolean = false

    override fun readArguments(bundle: Bundle) {
    }

    override fun initView() {
        binding.recycler.adapter = adapter
        binding.recycler.addOnScrollListener(object :
            PaginationScrollListener(binding.recycler.layoutManager as GridLayoutManager) {
            override fun isLastPage(): Boolean = isLastPage

            override fun isLoading(): Boolean = isLoading

            override fun loadMoreItems() {
                isLoading = true
                mainViewModel.getMoviesList(page++)
            }
        })
    }

    override fun initObservers() {
        mainViewModel.liveData.observe(viewLifecycleOwner) { list ->
            isLoading = false
            val updatedList = adapter.currentList.toMutableList()
            updatedList.addAll(list.results)
            adapter.submitList(updatedList)
        }
        mainViewModel.getMoviesList(page)
    }

    override fun onItemViewClick(movieId: Int) {
        findNavController().navigate(
            R.id.DetailsFragment,
            bundleOf(DetailsFragment.MOVIE_ID_KEY to movieId)
        )
    }
}