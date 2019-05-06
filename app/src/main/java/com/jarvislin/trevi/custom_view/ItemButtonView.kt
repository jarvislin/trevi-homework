package com.jarvislin.trevi.custom_view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.jarvislin.trevi.R
import kotlinx.android.synthetic.main.view_item_button.view.*

class ItemButtonView @kotlin.jvm.JvmOverloads
constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    FrameLayout(context, attrs, defStyleAttr), Highlightable {

    init {
        LayoutInflater.from(context).inflate(R.layout.view_item_button, this, true)
    }

    fun toggle() {
        button.isSelected = !button.isSelected
    }

    override fun highlight() {
        button.isSelected = true
        viewLeftHighlight.visibility = VISIBLE
        viewRightHighlight.visibility = VISIBLE
    }

    override fun clearHighlight() {
        button.isSelected = false
        viewLeftHighlight.visibility = GONE
        viewRightHighlight.visibility = GONE
    }

    fun button() = button
}