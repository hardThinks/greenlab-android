package ru.greenlab.greenlabadmin

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
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

    private val latestUsersFlow: Flow<List<User>> = flow {
        while(true) {
            val latestUsers = service.listUsers().awaitResponse().body() ?: emptyList()
            emit(latestUsers) // Emits the result of the request to the flow
            delay(5000) // Suspends the coroutine for some time
        }
    }


    init {
        viewModelScope.launch(coroutineHandler) {
            latestUsersFlow.collect() {
                users.value = it
            }
        }
    }
}
