package ru.greenlab.quiz.ui.register

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.google.accompanist.appcompattheme.AppCompatTheme
import com.google.gson.Gson
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import ru.greenlab.quiz.R
import ru.greenlab.quiz.ui.theme.GreenLabTheme

class RegisterFragment: Fragment() {
    private val viewModel by viewModels<RegisterViewModel>()
    private val gson = Gson()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val prefs = requireActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE)

        return ComposeView(requireContext()).apply {
            setContent {
                AppCompatTheme {
                    RegisterScreen(viewModel = viewModel) {
                        viewLifecycleOwner.lifecycleScope.launch {
                            viewModel.sendUser {
                                if (it.isSuccessful) {
                                    val json = gson.toJson(it.body())

                                    prefs.edit()
                                        .putString("user", json)
                                        .apply()

                                    navigateToOnboard()
                                } else {
                                    Toast.makeText(
                                        requireContext(),
                                        "Пользователь с таким номером телефона уже прошел тест",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }
                        // navigateToOnboard() // Для тестов
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
