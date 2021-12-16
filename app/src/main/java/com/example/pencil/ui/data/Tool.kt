package com.example.pencil.ui.data

import android.graphics.drawable.Drawable

data class Tool (
    val id: Int,
    var isFormat: Boolean = true,
    val type: CategoryType,
    val icon: Drawable? = null
        )

enum class CategoryType {
    BRUSH,
    COLOR,
    TEXT,
    BACKGROUND,
    CLEAR,
    ERASE,
    BACK
}