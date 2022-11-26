package ru.greenlab.quiz.dto

import java.io.Serializable

data class Question(
    val id: String,
    val value: String
): Serializable
