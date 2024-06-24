package com.example.bticapplication.extensions

import android.content.Context
import android.content.Intent
import android.content.res.Resources.getSystem
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.os.Parcelable
import android.util.TypedValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import kotlin.coroutines.cancellation.CancellationException

fun Int.toDp(): Int = (this / getSystem().displayMetrics.density).toInt()

fun Int.toPx(): Int = (this * getSystem().displayMetrics.density).toInt()

fun Float.spToPx(context: Context): Float =
    TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        this,
        context.resources.displayMetrics
    )

suspend fun <R, T> ViewModel.runBlocking(
    onBlock: suspend () -> T,
    onSuccess: suspend (T) -> R,
    onError: (Exception) -> R,
): R = withContext(Dispatchers.IO) {
    try {
        onSuccess(onBlock())
    } catch (cancellation: CancellationException) {
        throw cancellation
    } catch (exception: Exception) {
        onError(exception)
    }
}

inline fun <reified T : Parcelable> Intent.parcelable(key: String): T? = when {
    SDK_INT >= 33 -> getParcelableExtra(key, T::class.java)
    else -> @Suppress("DEPRECATION") getParcelableExtra(key) as? T
}

inline fun <reified T : Parcelable> Bundle.parcelable(key: String): T? = when {
    SDK_INT >= 33 -> getParcelable(key, T::class.java)
    else -> @Suppress("DEPRECATION") getParcelable(key) as? T
}

fun <T> Response<T>.runBlocking(onSuccess: (T) -> T, onThrowsException: () -> Exception): T =
    if (isSuccessful && body() != null) {
        onSuccess(checkNotNull(body()))
    } else {
        throw onThrowsException()
    }
