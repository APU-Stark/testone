package com.sagar.testprojone

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class KWAndConstTest {

    @Test
    fun testKw() {
        assertThat(KWAndConst.BASE_URL == "https://newsapi.org/v2/top-headlines")
    }
}