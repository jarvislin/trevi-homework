package com.jarvislin.trevi.rule

import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test

class ColumnRowRuleTest {
    @Test
    fun invalid_case_1() {
        val rule = ColumnRowRule("0", "1")
        assertFalse(rule.isValid())
    }

    @Test
    fun invalid_case_2() {
        val rule = ColumnRowRule("", "1")
        assertFalse(rule.isValid())
    }

    @Test
    fun invalid_case_3() {
        val rule = ColumnRowRule("3", "-1")
        assertFalse(rule.isValid())
    }

    @Test
    fun invalid_case_4() {
        val rule = ColumnRowRule("q", "$")
        assertFalse(rule.isValid())
    }

    @Test
    fun valid_case_1() {
        val rule = ColumnRowRule("1", "1")
        assertTrue(rule.isValid())
    }

    @Test
    fun valid_case_2() {
        val rule = ColumnRowRule("20", "5")
        assertTrue(rule.isValid())
    }
}