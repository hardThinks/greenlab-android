package ru.greenlab.quiz.ui.quiz

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import ru.greenlab.quiz.dto.Question
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor() : ViewModel() {
    val questions = MutableStateFlow(emptyList<Question>())
    val answers = MutableStateFlow(emptyList<String>())

    init {
        questions.value = listOf(
            Question("123", "123?"),
            Question("123", "What?"),
            Question("123", "Why?")
        )
    }
}
