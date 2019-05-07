package com.jarvislin.trevi

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.jarvislin.trevi.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = "Trevi Homework"

        buttonQuestion1.setOnClickListener { Question1Activity.start(this) }
        buttonQuestion2.setOnClickListener { Question2Activity.start(this) }
        buttonQuestion3.setOnClickListener { Question3Activity.start(this) }
    }
}
