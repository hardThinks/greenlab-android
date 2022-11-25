package ru.greenlab.quiz.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ru.greenlab.quiz.ui.theme.GreenLabTheme

class RegisterFragment: Fragment() {
    val viewModel by viewModels<RegisterViewModel>()

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
                    }
                }
            }
        }
    }
}
