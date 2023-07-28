package com.karel.movies.presentation.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.karel.core.presentation.image_carousel.model.ImageCarouselModel
import com.karel.movies.data.api.MovieService
import com.karel.movies.data.database.MovieDatabase
import com.karel.movies.data.repository.MovieRepositoryImpl
import com.karel.movies.databinding.FragmentMovieHomeBinding
import com.karel.movies.domain.usecase.UseCaseGetFavoriteMovies
import com.karel.movies.domain.usecase.UseCaseGetTopTenMoviesBySearchTerm

class MovieHomeFragment : Fragment() {

    private lateinit var viewModel: MovieHomeViewModel
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
        val viewModelFactory = MovieHomeViewModelFactory(
            useCaseGetTopTenMoviesBySearchTerm = UseCaseGetTopTenMoviesBySearchTerm(repository),
            useCaseGetFavoriteMovies = UseCaseGetFavoriteMovies(repository),
        )
        viewModel = ViewModelProvider(this, viewModelFactory)[MovieHomeViewModel::class.java]
        viewModel.onViewCreated()
    }

    private fun observeViewModel() {
        viewModel.loading.observe(viewLifecycleOwner) {
            showProgressIndicator(it)
        }
        viewModel.error.observe(viewLifecycleOwner) {

        }
        viewModel.favoriteMovies.observe(viewLifecycleOwner) { movieListEntity ->
            val imageCarouselModel = ImageCarouselModel(
                title = "Favorites",
                imageUriList = movieListEntity.transformMoviesToUriList,
                emptyStateText = "Add movies to your favorites"
            )
            binding.favouritesCarousel.renderWithModel(imageCarouselModel)
        }
        viewModel.residentEvilMovies.observe(viewLifecycleOwner) { movieListEntity ->
            val imageCarouselModel = ImageCarouselModel(
                title = "Resident Evil",
                imageUriList = movieListEntity.transformMoviesToUriList
            )
            binding.residentEvilCarousel.renderWithModel(imageCarouselModel)
        }
        viewModel.terminatorMovies.observe(viewLifecycleOwner) { movieListEntity ->
            val imageCarouselModel = ImageCarouselModel(
                title = "Terminator",
                imageUriList = movieListEntity.transformMoviesToUriList
            )
            binding.terminatorCarousel.renderWithModel(imageCarouselModel)
        }
    }

    private fun addViewListeners() {
    }

    private fun showProgressIndicator(isVisible: Boolean) {
        binding.progressIndicator.isVisible = isVisible
    }

}