package ru.greenlab.quiz.dto

import java.io.Serializable

data class User(
    val city: String,
    val id: String = "",
    val name: String,
    val phone_number: String
): Serializable
