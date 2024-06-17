package com.example.bticapplication.customview

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity.CENTER_VERTICAL
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.example.bticapplication.R

class MyTextField @JvmOverloads constructor(
    context: Context,
    private val attributeSet: AttributeSet? = null,
    private val defStyleAttr: Int = 0
) : LinearLayout(context, attributeSet, defStyleAttr) {

    private lateinit var editText: EditText
    var text: String
        get() = editText.text.toString()
        set(value) {
            editText.setText(value)
        }

    init {
        copyAttribute()
    }

    private fun copyAttribute() {
        orientation = HORIZONTAL
        this.background = ContextCompat.getDrawable(context, R.drawable.my_text_background)

        val typedArray = context.obtainStyledAttributes(
            attributeSet,
            R.styleable.MyTextField,
            defStyleAttr,
            0
        )

        val prefixImage = typedArray.getResourceId(R.styleable.MyTextField_prefixImage, 0)
        if (prefixImage != 0) {
            val prefixImageSize =
                typedArray.getDimension(R.styleable.MyTextField_prefixImageSize, 0F).toInt()
            val imageView = ImageView(context).apply {
                layoutParams = LayoutParams(prefixImageSize, prefixImageSize)
                gravity = CENTER_VERTICAL
            }
            imageView.setImageResource(prefixImage)
            this.addView(imageView)

            val divider = View(context).apply {
                val dividerPadding = resources.getDimension(R.dimen.my_text_divider_padding).toInt()
                layoutParams = LayoutParams(
                    resources.getDimension(R.dimen.divider_width).toInt(),
                    LayoutParams.MATCH_PARENT
                ).apply {
                    setMargins(
                        dividerPadding,
                        0,
                        dividerPadding,
                        0
                    )
                }
                setBackgroundColor(ContextCompat.getColor(context, R.color.mono3))
            }

            this.addView(divider)
        }

        val hint = typedArray.getText(R.styleable.MyTextField_hint).toString()
        val textSize = typedArray.getFloat(R.styleable.MyTextField_textSize, 0F)
        val inputType = typedArray.getInt(R.styleable.MyTextField_myInputType, 0)
        val textColor = typedArray.getColor(R.styleable.MyTextField_textColor, Color.BLACK)
        val type = ResourcesCompat.getFont(context, R.font.roboto_medium)
        editText = EditText(context).apply {
            setHint(hint)
            setTextSize(textSize)
            typeface = type
            setBackgroundColor(Color.WHITE)
            setInputType(inputType)
            setTextColor(textColor)
            layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        }
        this.addView(editText)

        typedArray.recycle()
    }
}