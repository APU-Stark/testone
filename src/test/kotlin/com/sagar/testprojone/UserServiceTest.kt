package com.sagar.testprojone

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.AdditionalAnswers.returnsFirstArg
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import java.util.*

@ExtendWith(MockitoExtension::class)
class UserServiceTest {

    @Mock
    private lateinit var testUserRepository: TestUserRepository

    @Mock
    private lateinit var externalServerPingMaster: ExternalServerPingMaster

    @InjectMocks
    private lateinit var userService: UserService

    @Test
    fun testSaveUserSuccess() {
        val userNameToSave = "Sagar"
        `when`(testUserRepository.save(any())).then(returnsFirstArg<TestUser>())
        val savedUser = userService.saveUser(userNameToSave)
        assertEquals(savedUser.name, userNameToSave)
    }

    @Test
    fun testGetUserByIdPositive() {
        val userToTest = TestUser(name = "Sagar")
        `when`(testUserRepository.findById(userToTest.id)).thenReturn(Optional.of(userToTest))
        val getUserById = userService.getUser(userToTest.id)
        assertThat(getUserById != null)
        assertThat(userToTest.id == getUserById!!.id)
        assertThat(userToTest.name == getUserById.name)
    }

    @Test
    fun testGetUserByIdNegative() {
        val badId = UUID.randomUUID().toString()
        `when`(testUserRepository.findById(badId)).thenReturn(Optional.empty())
        val getUserById = userService.getUser(badId)
        assertThat(getUserById == null)
    }
}