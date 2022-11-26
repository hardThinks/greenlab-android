package ru.greenlab.quiz.ui.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import ru.greenlab.quiz.dto.Category
import ru.greenlab.quiz.retrofit.ApiClient
import ru.greenlab.quiz.retrofit.BASE_URL
import ru.greenlab.quiz.retrofit.GreenLabService
import javax.inject.Inject

@HiltViewModel
class OnboardViewModel @Inject constructor() : ViewModel() {
    private val retrofit = ApiClient.getClient(BASE_URL)

    private val service = retrofit.create<GreenLabService>()

    val categories = MutableStateFlow<List<Category>>(emptyList())

    init {
        viewModelScope.launch {
            val response = service.listCategories().awaitResponse()
            response.body()?.let { categories.value = it }
        }
    }
}
