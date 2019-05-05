package com.jarvislin.trevi

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.jarvislin.trevi.base.BaseActivity
import com.jarvislin.trevi.base.BaseView
import com.jarvislin.trevi.rule.ColumnRowRule
import kotlinx.android.synthetic.main.activity_question1.*

class Question2Activity :BaseActivity(), Question2View {

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, Question2Activity::class.java))
        }
    }

    private val presenter = Question2Presenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question1)

        buttonSubmit.setOnClickListener {
            presenter.onSubmit(editColumn.text.toString(), editRow.text.toString())
        }
    }

    override fun directToResult(column: Int, row: Int) {
        ResultActivity.start(this, column, row)
    }
}

interface Question2View:BaseView {
    fun directToResult(column: Int, row: Int)
}

class Question2Presenter(private val view :Question2View) {
    fun onSubmit(column: String, row: String) {
        val rule = ColumnRowRule(column, row)
        if (rule.isValid()) {
            view.directToResult(column.toInt(), row.toInt())
        } else {
            view.showToast("Invalid parameters.")
        }
    }

}

