package com.example.bticapplication.extensions

import android.content.Context
import android.content.res.Resources.getSystem
import android.util.Log
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
    onBlock: suspend () -> Response<T>,
    onSuccess: suspend (T) -> R,
    onError: (Exception) -> R,
): R = withContext(Dispatchers.IO) {
    try {
        val response = onBlock()
        if (response.isSuccessful && response.body() != null) {
            onSuccess(checkNotNull(response.body()))
        } else {
            val message = response.errorBody().toString()
            onError(Exception(message))
        }
    } catch (cancellation: CancellationException) {
        throw cancellation
    } catch (exception: Exception) {
        onError(exception)
    }
}
