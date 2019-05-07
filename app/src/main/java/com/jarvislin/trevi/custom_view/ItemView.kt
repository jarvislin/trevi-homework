package com.jarvislin.trevi.custom_view

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.jarvislin.trevi.R
import kotlinx.android.synthetic.main.view_item.view.*

class ItemView @kotlin.jvm.JvmOverloads
constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    FrameLayout(context, attrs, defStyleAttr),Highlightable {

    private var color: Int = 0

    init {
        LayoutInflater.from(context).inflate(R.layout.view_item, this, true)
    }

    fun setBlockBackgroundColor(color: Int) {
        viewColorBlock.setBackgroundColor(color)
    }

    fun showRandom() {
        textRandom.visibility = VISIBLE
    }

    fun hideRandom() {
        textRandom.visibility = GONE
    }

    override fun highlight() {
        val layerDrawable = ContextCompat.getDrawable(context, R.drawable.item_highlight_background) as LayerDrawable
        val layerBackground = layerDrawable.findDrawableByLayerId(R.id.background) as GradientDrawable
        layerBackground.setColor(color)
        background = layerDrawable
    }

    override fun clearHighlight() {
        setItemBackgroundColor(color)
    }

    fun setItemBackgroundColor(color: Int) {
        this.color = color
        val layerDrawable = ContextCompat.getDrawable(context, R.drawable.item_background) as LayerDrawable
        val layerBackground = layerDrawable.findDrawableByLayerId(R.id.background) as GradientDrawable
        layerBackground.setColor(color)
        background = layerDrawable
    }
}

interface Highlightable {
    fun highlight()
    fun clearHighlight()
}