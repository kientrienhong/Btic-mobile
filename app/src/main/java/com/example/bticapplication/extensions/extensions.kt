package com.example.bticapplication.extensions

import android.content.Context
import android.content.res.Resources.getSystem
import android.util.TypedValue

fun Int.toDp(): Int = (this / getSystem().displayMetrics.density).toInt()

fun Int.toPx(): Int = (this * getSystem().displayMetrics.density).toInt()

fun Float.spToPx(context: Context): Float =
    TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        this,
        context.resources.displayMetrics
    )
