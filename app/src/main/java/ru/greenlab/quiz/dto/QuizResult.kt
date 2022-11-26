package ru.greenlab.quiz.dto

import java.io.Serializable

data class QuizResult(
    val result: List<SingleResult>,
    val user_id: String
): Serializable

data class SingleResult(
    val answer: String,
    val question_id: String
): Serializable
