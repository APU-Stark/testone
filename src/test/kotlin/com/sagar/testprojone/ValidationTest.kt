package com.sagar.testprojone

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import javax.validation.Validation

class ValidationTest {

    private val validator = Validation.buildDefaultValidatorFactory().validator

    @Test
    fun testSaveUserRequest() {
        val saveUserRequest = SaveUserRequest()
        val violations = validator.validate(saveUserRequest)
        assertThat(violations).isNotEmpty()
        assertThat(violations.size == 1)
        assertEquals(violations.first().message, "Please provide Name")
    }
}