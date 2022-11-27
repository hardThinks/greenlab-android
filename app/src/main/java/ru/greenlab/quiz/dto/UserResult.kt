package ru.greenlab.quiz.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class UserResult(
    val quiz_result_items: List<SingleUserResult>
): Serializable, Parcelable

@Parcelize
data class SingleUserResult(
    val category: Category,
    val percentage: Int
): Serializable, Parcelable
