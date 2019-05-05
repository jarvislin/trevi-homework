package com.jarvislin.trevi.base

import android.support.v7.app.AppCompatActivity
import android.widget.Toast

abstract class BaseActivity :AppCompatActivity(), BaseView {
    override fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

}