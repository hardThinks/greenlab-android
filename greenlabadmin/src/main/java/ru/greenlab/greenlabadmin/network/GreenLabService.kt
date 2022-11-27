package ru.greenlab.greenlabadmin.network

import retrofit2.Call
import retrofit2.http.GET
import ru.greenlab.greenlabadmin.dto.User

const val BASE_URL = "http://192.168.171.153:8000/v1/"

interface GreenLabService {
    @GET("users/")
    fun listUsers(): Call<List<User>>
}
