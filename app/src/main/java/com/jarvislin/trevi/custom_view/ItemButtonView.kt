package com.jarvislin.trevi.custom_view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.jarvislin.trevi.R
import kotlinx.android.synthetic.main.view_item_button.view.*

class ItemButtonView  @kotlin.jvm.JvmOverloads
constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
FrameLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.view_item_button, this, true)
    }

    fun setClickListener(listener: OnClickListener) {
        button.setOnClickListener(listener)
    }
}