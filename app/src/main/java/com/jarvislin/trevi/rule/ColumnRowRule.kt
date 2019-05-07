package com.jarvislin.trevi.rule

import android.text.TextUtils

class ColumnRowRule(private val column: String, private val row: String) : BaseRule() {

    override fun isValid(): Boolean {
        return try {
            if (TextUtils.isDigitsOnly(column) && TextUtils.isDigitsOnly(row)) {
                column.toInt() > 0 && row.toInt() > 0
            } else {
                false
            }
        } catch (ex: NumberFormatException) {
            return false
        }
    }
}