package ru.greenlab.quiz.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.*
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint
import ru.greenlab.quiz.R
import ru.greenlab.quiz.ui.theme.GreenLabTheme

@AndroidEntryPoint
class OnboardFragment: Fragment() {
    private val viewModel by viewModels<OnboardViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                GreenLabTheme {
                    val categories by viewModel.categories.collectAsState()

                    OnBoardScreen(categories) {
                        Navigation.findNavController(
                            requireActivity().findViewById(R.id.nav_host_fragment))
                            .navigate(R.id.action_onboardFragment_to_quizFragment)
                    }
                }
            }
        }
    }
}
