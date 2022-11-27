package ru.greenlab.greenlabadmin

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.awaitResponse
import retrofit2.create
import ru.greenlab.greenlabadmin.dto.User
import ru.greenlab.greenlabadmin.network.ApiClient
import ru.greenlab.greenlabadmin.network.BASE_URL
import ru.greenlab.greenlabadmin.network.GreenLabService
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    val coroutineHandler = CoroutineExceptionHandler { _, exception ->
        Log.e("Network", "Caught $exception")
    }

    private val retrofit = ApiClient.getClient(BASE_URL)
    private val service = retrofit.create<GreenLabService>()

    val users = MutableStateFlow<List<User>>(emptyList())

    init {
        viewModelScope.launch(coroutineHandler) {
            val response = service.listUsers().awaitResponse()
            users.value = response.body() ?: emptyList()
        }
    }
}
