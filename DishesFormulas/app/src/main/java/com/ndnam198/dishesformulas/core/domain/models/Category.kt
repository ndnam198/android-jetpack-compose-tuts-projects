package com.ndnam198.dishesformulas.core.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
    val strCategory: String,
    val strCategoryDescription: String,
    val idCategory: String,
    val strCategoryThumb: String
) : Parcelable {
    companion object {
        val empty = Category("", "", "", "")
    }
}

data class Categories(
    val categories: List<Category>
)
