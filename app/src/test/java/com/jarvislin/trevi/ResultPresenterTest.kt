package com.jarvislin.trevi

import com.nhaarman.mockito_kotlin.mock
import org.junit.Assert.*
import org.junit.Test

class ResultPresenterTest {
    private val view = mock<ResultView>()
    private val presenter = ResultPresenter(view)

    @Test
    fun randomPair_valid_1() {
        val actual = presenter.getRandomPair(1, 1)
        assertEquals(Pair(1, 1), actual)
    }

    @Test
    fun randomPair_valid_2() {
        val actual = presenter.getRandomPair(5, 10)
        assertTrue(actual.first in 1..5)
        assertTrue(actual.second in 1..10)
    }
}