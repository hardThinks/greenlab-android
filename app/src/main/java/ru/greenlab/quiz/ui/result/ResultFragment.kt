package ru.greenlab.quiz.ui.result

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.google.accompanist.appcompattheme.AppCompatTheme
import com.google.gson.Gson
import ru.greenlab.quiz.dto.UserResult

class ResultFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val gson = Gson()
        val prefs = requireActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE)

        val results = gson.fromJson(
            prefs.getString("listUserResult", null),
            UserResult::class.java
        )

        return ComposeView(requireContext()).apply {
            setContent {
                AppCompatTheme {
                    ResultScreen(results = results.quiz_result_items)
                }
            }
        }
    }
}
