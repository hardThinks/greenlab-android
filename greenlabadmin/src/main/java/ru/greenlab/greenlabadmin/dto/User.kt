package ru.greenlab.greenlabadmin.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class User(
    val city: String,
    val id: String = "",
    val name: String,
    val phone_number: String,
    val quiz_results: List<SingleResult>
): Serializable, Parcelable

@Parcelize
data class SingleResult(
    val quiz_result_items: List<CategoryAndPercent>
): Serializable, Parcelable

@Parcelize
data class CategoryAndPercent(
    val category: Category,
    val percentage: Int
): Serializable, Parcelable
