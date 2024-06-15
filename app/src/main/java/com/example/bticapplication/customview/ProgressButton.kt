package com.example.bticapplication.customview

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.core.widget.TextViewCompat
import com.example.bticapplication.R
import com.google.android.material.progressindicator.CircularProgressIndicator

class ProgressButton @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attributeSet, defStyleAttr) {
    private lateinit var textView: TextView
    private lateinit var progressBar: CircularProgressIndicator

    init {
        LayoutInflater.from(context).inflate(R.layout.progress_view, this, true)
        initView()
        // Obtain a TypedArray containing attribute values
        val typedArray = context.obtainStyledAttributes(
            attributeSet,
            R.styleable.ProgressButton,
            defStyleAttr,
            0
        )
        val isLoading = typedArray.getBoolean(R.styleable.ProgressButton_isLoading, false)
        textView.apply {
            text = typedArray.getText(R.styleable.ProgressButton_text)
            textSize = typedArray.getFloat(R.styleable.ProgressButton_textSizeProgressButton, 0f)
            isVisible = !isLoading
            setTextColor(
                typedArray.getColor(R.styleable.ProgressButton_btnTextColor, Color.BLACK)
            )
            gravity = Gravity.CENTER
        }
        TextViewCompat.setTextAppearance(
            textView,
            typedArray.getResourceId(R.styleable.ProgressButton_buttonTextStyle, 0)
        )
        progressBar.isVisible = isLoading
        background = GradientDrawable().apply {
            setColor(
                typedArray.getColor(
                    R.styleable.ProgressButton_buttonBackgroundColor,
                    0xFFFFFF
                )
            )
            cornerRadius = typedArray.getFloat(R.styleable.ProgressButton_buttonBorderRadius, 0f)
        }
        // Don't forget to recycle the TypedArray
        typedArray.recycle()
    }

    private fun initView() {
        textView = findViewById(R.id.text_btn)
        progressBar = findViewById(R.id.progress_bar)
    }

    fun setIsLoading(isLoading: Boolean) {
        progressBar.isVisible = isLoading
        textView.isVisible = !isLoading
    }
}