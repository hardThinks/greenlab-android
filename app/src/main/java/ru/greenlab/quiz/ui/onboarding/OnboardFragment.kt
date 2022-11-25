package ru.greenlab.quiz.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import ru.greenlab.quiz.ui.theme.GreenLabTheme

@AndroidEntryPoint
class OnboardFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                GreenLabTheme {
                    OnBoardScreen()
                }
            }
        }
    }
}