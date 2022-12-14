package ru.greenlab.quiz.networkUtils

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import ru.greenlab.quiz.dto.*

const val BASE_URL = "http://192.168.171.153:8000/v1/"

interface GreenLabService {
    @POST("users/create/")
    fun createUser(@Body user: User): Call<User>

    @GET("quiz/questions")
    fun listQuestions(): Call<List<Question>>

    @POST("quiz/finish")
    fun finishQuiz(@Body result: QuizResult): Call<UserResult>

    @GET("users/")
    fun listUsers(): Call<List<User>>

    @GET("categories/")
    fun listCategories(): Call<List<Category>>
}
