package com.jarvislin.trevi

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.FrameLayout
import com.jarvislin.trevi.base.BaseActivity
import com.jarvislin.trevi.base.BaseView
import com.jarvislin.trevi.custom_view.ItemButtonView
import com.jarvislin.trevi.custom_view.ItemView
import kotlinx.android.synthetic.main.activity_result.*
import kotlin.random.Random

class ResultActivity : BaseActivity(), ResultView {

    companion object {
        fun start(context: Context, column: Int, row: Int, enableTimer: Boolean = false) {
            val intent = Intent(context, ResultActivity::class.java)
            intent.putExtra(KEY_COLUMN, column)
            intent.putExtra(KEY_ROW, row)
            intent.putExtra(KEY_ENABLE_TIMER, enableTimer)
            context.startActivity(intent)
        }

        private const val KEY_ENABLE_TIMER = "ENABLE_TIMER"
        private const val KEY_COLUMN = "COLUMN"
        private const val KEY_ROW = "ROW"
    }

    private val presenter = ResultPresenter(this)
    private val column: Int by lazy { intent.getIntExtra(KEY_COLUMN, -1) }
    private val row: Int by lazy { intent.getIntExtra(KEY_ROW, -1) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        initLayout()
    }

    private fun initLayout() {
        flexbox.post {
            val width = flexbox.width
            val height = flexbox.height

            val itemWidth = width / column
            val itemHeight = height / (row + 1) // additional +1 for the layout of button

            for (i in 1..row + 1) {
                val red = (0..255).random()
                val green = (0..255).random()
                val blue = (0..255).random()
                for (j in 1..column) {
                    val param = FrameLayout.LayoutParams(itemWidth, itemHeight)
                    if (i == row + 1) {
                        val itemButtonView = ItemButtonView(this)
                        itemButtonView.layoutParams = param
                        flexbox.addView(itemButtonView)
                    } else {
                        val itemView = ItemView(this)
                        itemView.setItemBackgroundColor(Color.argb(80, red, green, blue))
                        itemView.setBlockBackgroundColor(Color.rgb(red, green, blue))
                        itemView.layoutParams = param
                        flexbox.addView(itemView)
                    }
                }
            }

        }
    }


}

interface ResultView : BaseView

class ResultPresenter(val view: ResultView) {

}