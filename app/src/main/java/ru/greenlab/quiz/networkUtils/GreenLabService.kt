package ru.greenlab.quiz.networkUtils

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import ru.greenlab.quiz.dto.Category
import ru.greenlab.quiz.dto.Question
import ru.greenlab.quiz.dto.QuizResult
import ru.greenlab.quiz.dto.User

const val BASE_URL = "http://172.20.10.3:8000/v1/"

interface GreenLabService {
    @POST("users/create/")
    fun createUser(@Body user: User): Call<User>

    @GET("quiz/questions/")
    fun listQuestions(): Call<List<Question>>

    @POST("quiz/finish/")
    fun finishQuiz(@Body result: QuizResult)

    @GET("users/")
    fun listUsers(): Call<List<User>>

    @GET("categories/")
    fun listCategories(): Call<List<Category>>
}
