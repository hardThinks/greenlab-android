package ru.greenlab.quiz.networkUtils

import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler

val coroutineHandler = CoroutineExceptionHandler { _, exception ->
    Log.e("Network", "Caught $exception")
}
