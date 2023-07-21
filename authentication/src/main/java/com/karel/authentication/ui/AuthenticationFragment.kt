package com.karel.authentication.ui

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.karel.authentication.R
import com.karel.authentication.databinding.FragmentAuthenticationBinding
import com.karel.core.awaitTransitionComplete
import kotlinx.coroutines.launch

class AuthenticationFragment : Fragment() {

    private lateinit var authenticationViewModel: AuthenticationViewModel
    private lateinit var binding: FragmentAuthenticationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthenticationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startEnterTransition()
        init()
    }

    private fun init() {
        authenticationViewModel = ViewModelProvider(this)[AuthenticationViewModel::class.java]
        authenticationViewModel.userNameError.observe(viewLifecycleOwner) {
            binding.emailEditText.error = it
        }
        authenticationViewModel.passwordError.observe(viewLifecycleOwner) {
            binding.passwordEditText.error = it
        }
        authenticationViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            showProgressIndicator(isLoading)
        }
        authenticationViewModel.error.observe(viewLifecycleOwner) {
            showToast(it)
        }

        binding.loginButton.setOnClickListener {
            hideKeyboard()
            val userName = binding.emailEditText.editText?.text?.toString() ?: ""
            val password = binding.passwordEditText.editText?.text?.toString() ?: ""
            authenticationViewModel.onLoginClicked(userName, password)
        }
        binding.signupButton.setOnClickListener {
            hideKeyboard()
            val userName = binding.emailEditText.editText?.text?.toString() ?: ""
            val password = binding.passwordEditText.editText?.text?.toString() ?: ""
            authenticationViewModel.onSignupClicked(userName, password)
        }

        binding.emailEditText.editText?.addTextChangedListener {
            binding.emailEditText.error = null
        }
        binding.passwordEditText.editText?.addTextChangedListener {
            binding.passwordEditText.error = null
        }
    }

    private fun startEnterTransition() {
        viewLifecycleOwner.lifecycleScope.launch {
            binding.motionLayout.setTransition(R.id.transition_start)
            binding.motionLayout.transitionToEnd()
            binding.motionLayout.awaitTransitionComplete()
            binding.motionLayout.setTransition(R.id.transition_logo_fade_in)
            binding.motionLayout.transitionToEnd()
            binding.motionLayout.awaitTransitionComplete()
            binding.motionLayout.setTransition(R.id.transition_logo_slide_into_position)
            binding.motionLayout.transitionToEnd()
            binding.motionLayout.awaitTransitionComplete()
            binding.motionLayout.setTransition(R.id.transition_content_fade_in)
            binding.motionLayout.transitionToEnd()
            binding.motionLayout.awaitTransitionComplete()
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    private fun showProgressIndicator(isVisible: Boolean) {
        binding.progressIndicator.isVisible = isVisible
    }

    private fun hideKeyboard() {
        val view = activity?.currentFocus
        if (view != null) {
            val inputManager =
                activity?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(
                view.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
    }
}