package com.example.pencil.ui.data

import android.content.Context
import android.graphics.drawable.Drawable
import com.example.pencil.R
import com.example.pencil.util.getIcon


fun getToolList(context: Context) =
    mutableListOf(
        Tool(1, false, CategoryType.BRUSH, context.resources.getIcon(R.drawable.ic_brush)),
        Tool(2, false, CategoryType.COLOR, context.resources.getIcon(R.drawable.ic_colors)),
        Tool(
            3,
            false,
            CategoryType.BACKGROUND,
            context.resources.getIcon(R.drawable.ic_background)
        ),
        Tool(4, false, CategoryType.TEXT, context.resources.getIcon(R.drawable.ic_text)),
        Tool(
            5,
            false,
            CategoryType.CLEAR,
            context.resources.getIcon(R.drawable.ic_rease_everything)
        ),
    )

fun getBackgroundList(context: Context) =
    mutableListOf(
        Tool(
            id = -1,
            type = CategoryType.BACK,
            icon = context.resources.getIcon(R.drawable.ic_back),
        ),
        Tool(
            id = 1,
            type = CategoryType.BACKGROUND,
            icon = context.resources.getIcon(R.drawable.ic_background),
        ),
        Tool(
            id = 2,
            type = CategoryType.BACKGROUND,
            icon = context.resources.getIcon(R.drawable.ic_background),
        ),
        Tool(
            id = 3,
            type = CategoryType.BACKGROUND,
            icon = context.resources.getIcon(R.drawable.ic_background),
        ),
        Tool(
            id = 4,
            type = CategoryType.BACKGROUND,
            icon = context.resources.getIcon(R.drawable.ic_background),
        ),

        )