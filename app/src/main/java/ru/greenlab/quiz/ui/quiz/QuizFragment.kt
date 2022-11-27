package ru.greenlab.quiz.ui.quiz

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.google.accompanist.appcompattheme.AppCompatTheme
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.greenlab.quiz.R
import ru.greenlab.quiz.dto.User

@AndroidEntryPoint
class QuizFragment : Fragment() {
    private val viewModel by viewModels<QuizViewModel>()

    private lateinit var prefs: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        prefs = requireActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val gson = Gson()
        val user = gson.fromJson(prefs.getString("user", ""), User::class.java)
        viewModel.userId = user.id

        return ComposeView(requireContext()).apply {
            setContent {
                AppCompatTheme {
                    val questions by viewModel.questions.collectAsState()

                    if (questions.isNotEmpty()) {
                        QuizScreen(
                            questions = questions,
                            onSubmitClick = {
                                viewModel.answers.value += toEnglishEquivalent(it)
                            },
                            onFinalSubmit = {
                                viewLifecycleOwner.lifecycleScope.launch {
                                    viewModel.sendResult {
                                        if (it.isSuccessful) {
                                            val bundle = Bundle()
                                            bundle.putParcelable("listUserResult", it.body())

                                            navigateToResult(bundle)
                                        } else {
                                            Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                                        }
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
    }

    private fun navigateToResult(bundle: Bundle) {
        Navigation.findNavController(requireActivity().findViewById(R.id.nav_host_fragment))
            .navigate(R.id.action_quizFragment_to_resultFragment, bundle)
    }

    private fun toEnglishEquivalent(answer: String): String {
        return when (answer) {
            Answers.yes[1] -> Answers.yes[0]
            Answers.no[1] -> Answers.no[0]
            Answers.mostlyNo[1] -> Answers.mostlyNo[0]
            Answers.mostlyYes[1] -> Answers.mostlyYes[0]
            Answers.dontKnow[1] -> Answers.dontKnow[0]
            else -> { "" }
        }
    }
}
