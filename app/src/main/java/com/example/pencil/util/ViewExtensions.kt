package com.example.pencil.util

import android.content.res.Resources
import android.graphics.drawable.Drawable
import androidx.core.content.res.ResourcesCompat

fun Resources.getIcon(id :Int): Drawable? {
    return ResourcesCompat.getDrawable(this,id, null)
}