package com.jarvislin.trevi

import android.content.Context
import android.content.Intent
import android.os.Bundle

class Question3Activity :Question2Activity(){
    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, Question3Activity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = "題目3"
    }

    override fun directToResult(column: Int, row: Int) {
        ResultActivity.start(this, column, row, true)
    }
}