package com.jarvislin.trevi

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.jarvislin.trevi.base.BaseActivity
import com.jarvislin.trevi.base.BaseView
import com.jarvislin.trevi.rule.ColumnRowRule
import kotlinx.android.synthetic.main.activity_question1.*

class Question1Activity : BaseActivity(), BaseView {

    private val presenter = Question1Presenter(this)

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, Question1Activity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question1)

        buttonSubmit.setOnClickListener {
            presenter.onSubmit(editColumn.text.toString(), editRow.text.toString())
        }
    }
}

class Question1Presenter(private val view: BaseView) {
    fun onSubmit(column: String, row: String) {
        val rule = ColumnRowRule(column, row)
        if (rule.isValid()) {
            view.showToast("Valid parameters.")
        } else {
            view.showToast("Invalid parameters.")
        }
    }

}