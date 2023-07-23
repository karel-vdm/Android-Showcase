package com.karel.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import com.karel.home.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialiseView()
    }

    private fun initialiseView() {
        createViewModel()
        observeViewModel()
        addViewListeners()
        startEnterTransition()
    }

    private fun startEnterTransition() {
    }

    private fun addViewListeners() {
        binding.dogCardView.setOnClickListener {

        }
    }

    private fun observeViewModel() {
    }

    private fun createViewModel() {
    }

    private fun navigateToHomeFragment() {
        val request = NavDeepLinkRequest.Builder
            .fromUri("android-app://com.karel.dog/DogFragment".toUri())
            .build()
        findNavController().navigate(request)
    }
}