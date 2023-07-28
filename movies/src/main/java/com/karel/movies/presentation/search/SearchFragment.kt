package com.karel.movies.presentation.search

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.karel.movies.data.api.MovieService
import com.karel.movies.data.database.MovieDatabase
import com.karel.movies.data.repository.MovieRepositoryImpl
import com.karel.movies.databinding.FragmentMovieHomeBinding
import com.karel.movies.domain.usecase.UseCaseGetMovies

class SearchFragment : Fragment() {

    private lateinit var viewModel: SearchViewModel
    private lateinit var binding: FragmentMovieHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialiseView()
    }

    private fun initialiseView() {
        startEnterTransition()
        createViewModel()
        observeViewModel()
        addViewListeners()
    }

    private fun startEnterTransition() {
    }

    private fun createViewModel() {
        val context = context ?: return
        val database = MovieDatabase.getDatabase(context)
        val repository = MovieRepositoryImpl(
            MovieService.create(),
            database.movieDao()
        )
        val viewModelFactory = SearchViewModelFactory(
            useCaseGetMovies = UseCaseGetMovies(repository),
        )
        viewModel = ViewModelProvider(this, viewModelFactory)[SearchViewModel::class.java]
        viewModel.onViewCreated()
    }

    private fun observeViewModel() {
        viewModel.loading.observe(viewLifecycleOwner) {
            showProgressIndicator(it)
        }
        viewModel.error.observe(viewLifecycleOwner) {

        }
        viewModel.movies.observe(viewLifecycleOwner) { movieListEntity ->

        }
    }

    private fun addViewListeners() {
    }

    private fun showProgressIndicator(isVisible: Boolean) {
        binding.progressIndicator.isVisible = isVisible
    }

}