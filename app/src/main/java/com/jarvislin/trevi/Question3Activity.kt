package com.jarvislin.trevi

import android.content.Context
import android.content.Intent

class Question3Activity :Question2Activity(){
    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, Question3Activity::class.java))
        }
    }

    override fun directToResult(column: Int, row: Int) {
        ResultActivity.start(this, column, row, true)
    }
}