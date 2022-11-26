package ru.greenlab.quiz.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import ru.greenlab.quiz.R
import ru.greenlab.quiz.ui.theme.GreenLabTheme

class RegisterFragment: Fragment() {
    private val viewModel by viewModels<RegisterViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                GreenLabTheme {
                    RegisterScreen(viewModel = viewModel) {
                        viewModel.sendUser()
                        navigateToOnboard()
                    }
                }
            }
        }
    }

    private fun navigateToOnboard() {
        Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
            .navigate(R.id.action_registerFragment_to_onboardFragment)
    }
}
