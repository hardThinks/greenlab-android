package ru.greenlab.quiz.ui.register

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.awaitResponse
import retrofit2.create
import ru.greenlab.quiz.dto.User
import ru.greenlab.quiz.retrofit.ApiClient
import ru.greenlab.quiz.retrofit.BASE_URL
import ru.greenlab.quiz.retrofit.GreenLabService
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor() : ViewModel() {
    private val retrofit = ApiClient.getClient(BASE_URL)

    private val service = retrofit.create<GreenLabService>()

    val userName = mutableStateOf("")
    val userNumber = mutableStateOf("")
    val userCity = mutableStateOf("")

    suspend fun sendUser(
        callBack: (Response<User>) -> Unit
    ) {
        viewModelScope.launch {
            val response = service.createUser(
                User(
                    name = userName.value,
                    city = userCity.value,
                    phone_number = userNumber.value
                )
            ).awaitResponse()

            callBack(response)
        }

    }
}
