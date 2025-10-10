package com.dprog.common

import org.junit.Assert.assertEquals
import org.junit.Test

class MathsTest {
    @Test fun add_works() {
        assertEquals(5, Maths.add(2, 3))
    }

    @Test fun divide_work() {
        assertEquals(2, Maths.divide(4, 2))
    }

    @Test fun multiply_works() {
        assertEquals(6, Maths.multiply(2, 3))
    }
}
