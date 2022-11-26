package ru.greenlab.quiz.ui.onboarding

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
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
import ru.greenlab.quiz.retrofit.BASE_URL
import ru.greenlab.quiz.retrofit.GreenLabService
import javax.inject.Inject

@HiltViewModel
class OnboardViewModel @Inject constructor() : ViewModel() {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create<GreenLabService>()

    var categories = MutableStateFlow<List<Category>>(emptyList())

    init {
        viewModelScope.launch {
            val response = service.listCategories().awaitResponse()
            response.body()?.let { categories.value = it }
        }
    }
}
