package ru.greenlab.quiz.ui.quiz

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.awaitResponse
import retrofit2.create
import ru.greenlab.quiz.dto.Question
import ru.greenlab.quiz.dto.QuizResult
import ru.greenlab.quiz.dto.SingleResult
import ru.greenlab.quiz.dto.UserResult
import ru.greenlab.quiz.networkUtils.ApiClient
import ru.greenlab.quiz.networkUtils.BASE_URL
import ru.greenlab.quiz.networkUtils.GreenLabService
import ru.greenlab.quiz.networkUtils.coroutineHandler
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor() : ViewModel() {
    val questions = MutableStateFlow(emptyList<Question>())
    val answers = MutableStateFlow(emptyList<String>())

    lateinit var userId: String

    private val retrofit = ApiClient.getClient(BASE_URL)
    private val service = retrofit.create<GreenLabService>()

    init {
        // Для теста
        /*questions.value = listOf(
            Question("1", "123?"),
            Question("2", "What?"),
            Question("3", "Why?")
        )*/

        viewModelScope.launch(coroutineHandler) {
            val response = service.listQuestions().awaitResponse()
            questions.value = response.body() ?: emptyList()
        }
    }

    suspend fun sendResult(callback: (Response<UserResult>) -> Unit) {
        viewModelScope.launch(coroutineHandler) {
            val quizResult = buildResult()
            val response = service.finishQuiz(quizResult).awaitResponse()

            callback(response)
        }
    }

    private fun buildResult(): QuizResult {
        val testResults: MutableList<SingleResult> = mutableListOf()
        val questionList = questions.value
        val answerList = answers.value

        if (questionList.size == answerList.size) {
            for (i in questionList.indices) {
                testResults.add(SingleResult(answerList[i], questionList[i].id))
            }
        } else {
            Log.i("TAG", "buildResult: Different sizes")
        }

        return QuizResult(testResults, userId)
    }
}
