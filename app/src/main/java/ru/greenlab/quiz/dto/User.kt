package ru.greenlab.quiz.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class User(
    val city: String,
    val id: String = "",
    val name: String,
    val phone_number: String
): Serializable, Parcelable
