package ru.greenlab.quiz.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class Category(
    val id: String,
    val name: String,
    val description: String?
): Serializable, Parcelable
