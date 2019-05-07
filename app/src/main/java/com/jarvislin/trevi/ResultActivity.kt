package com.jarvislin.trevi

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.FrameLayout
import com.jakewharton.rxbinding2.view.RxView
import com.jarvislin.trevi.base.BaseActivity
import com.jarvislin.trevi.base.BaseView
import com.jarvislin.trevi.base.bindView
import com.jarvislin.trevi.custom_view.Highlightable
import com.jarvislin.trevi.custom_view.ItemButtonView
import com.jarvislin.trevi.custom_view.ItemView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_result.*
import timber.log.Timber
import java.util.concurrent.TimeUnit

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
    private val timerEnabled: Boolean by lazy { intent.getBooleanExtra(KEY_ENABLE_TIMER, false) }
    private val interval = Observable.interval(10L, TimeUnit.SECONDS)

    private var randomX = -1
    private var randomY = -1

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
                        itemView.setItemBackgroundColor(getLightColor(red, green, blue))
                        itemView.setBlockBackgroundColor(Color.rgb(red, green, blue))
                        itemView.layoutParams = param
                        itemView.id = column * row
                        flexbox.addView(itemView)
                    }
                }
            }
            if (timerEnabled) {
                initTimer()
                initTimerLayout()
            }
        }
    }


    private fun initTimer() {
        interval
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                clearHighlight()
                randomHighlight()
            }
            .bindView(this)
    }

    private fun initTimerLayout() {
        val buttonIndexStart = row * column
        val buttonIndexEnd = (row + 1) * column - 1
        for (i in buttonIndexStart..buttonIndexEnd) {
            RxView.clicks((flexbox.getChildAt(i) as ItemButtonView).button())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { clearHighlight() }
                .bindView(this)
        }
    }

    private fun randomHighlight() {
        val pair = presenter.getRandomPair(column, row)
        randomX = pair.first
        randomY = pair.second

        Timber.e("column = $randomX, row = $randomY")

        val item = flexbox.getChildAt(column * (randomY - 1) + randomX - 1) as ItemView
        item.showRandom()

        var offset = randomX - 1
        while (offset < column * (row + 1)) {
            val highlightItem = flexbox.getChildAt(offset) as Highlightable
            highlightItem.highlight()
            offset += column
        }
    }

    private fun clearHighlight() {
        if (randomX < 0 || randomY < 0) {
            return
        }
        val item = flexbox.getChildAt(column * (randomY - 1) + randomX - 1) as ItemView
        item.hideRandom()

        var offset = randomX - 1
        while (offset < column * (row + 1)) {
            val highlightItem = flexbox.getChildAt(offset) as Highlightable
            highlightItem.clearHighlight()
            offset += column
        }
    }

    private fun getLightColor(r: Int, g: Int, b: Int): Int {
        val max = listOf(r, g, b).max()
        val offset = 255 - max!!
        return Color.rgb(r + offset, g + offset, b + offset)
    }
}

interface ResultView : BaseView

class ResultPresenter(val view: ResultView) {
    fun getRandomPair(column: Int, row: Int): Pair<Int, Int> {
        return Pair((1..column).random(), (1..row).random())
    }

}