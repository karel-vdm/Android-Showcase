package com.karel.movies.presentation.container

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.karel.movies.presentation.favorite.FavoriteViewModel
import com.karel.movies.R
import com.karel.movies.databinding.FragmentMovieContainerBinding

class MovieContainerFragment : Fragment() {

    private lateinit var binding: FragmentMovieContainerBinding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieContainerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialiseView()
    }

    private fun initialiseView() {
        setupNavController()
        removeBottomNavigationInset()
        startEnterTransition()
        createViewModel()
        observeViewModel()
        addViewListeners()
    }

    private fun setupNavController() {
        navController = findNavController()
        binding.bottomNavigation.setupWithNavController(navController)
    }

    private fun removeBottomNavigationInset() {
        binding.bottomNavigation.setOnApplyWindowInsetsListener(null)
        binding.bottomNavigation.setPadding(0, 0, 0, 0)
    }

    private fun startEnterTransition() {
    }

    private fun createViewModel() {
    }

    private fun observeViewModel() {
    }

    private fun addViewListeners() {
        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.movieHomeFragment -> navigateToHome()
                R.id.favoriteFragment -> navigateToFavorites()
                R.id.searchFragment -> navigateToSearch()
                else -> false
            }
        }
    }

    private fun navigateToHome(): Boolean {
        return false
    }

    private fun navigateToFavorites(): Boolean {
        val request = NavDeepLinkRequest.Builder
            .fromUri("android-app://com.karel.movies.presentation.favorite.FavoriteMovieFragment".toUri())
            .build()
        navController.navigate(request)
        return true
    }

    private fun navigateToSearch(): Boolean {
        val request = NavDeepLinkRequest.Builder
            .fromUri("android-app://com.karel.movies.presentation.search.SearchFragment".toUri())
            .build()
        navController.navigate(request)
        return true
    }
}