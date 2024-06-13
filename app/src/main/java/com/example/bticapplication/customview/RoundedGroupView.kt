package com.example.bticapplication.customview

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.bticapplication.R
class RoundedGroupView @JvmOverloads constructor(
    private val context: Context,
    private val attrs: AttributeSet? = null,
    private val defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        copyAttribute()
    }

    fun updateView(backgroundColor: Int? = null, borderRadius: Float? = null) =
        copyAttribute(backgroundColor, borderRadius)

    private fun copyAttribute(backgroundColor: Int? = null, borderRadius: Float? = null) {
        val typedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.RoundedGroupView,
            defStyleAttr,
            0
        )

        val backgroundColor = backgroundColor ?: typedArray.getColor(
            R.styleable.RoundedGroupView_backgroundColor,
            0xFFFFFF
        )
        val borderRadius =
            borderRadius ?: typedArray.getDimension(R.styleable.RoundedGroupView_borderRadius, 0f)
        background = GradientDrawable().apply {
            setColor(backgroundColor)
            cornerRadius = borderRadius
        }

        typedArray.recycle()
    }
}