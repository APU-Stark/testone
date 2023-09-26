package com.sagar.testprojone

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.support.TestPropertySourceUtils
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Testcontainers


@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(initializers = [TestUserDbTest.DataSourceInitializer::class])
class TestUserDbTest {

    @Autowired
    private lateinit var testUserRepository: TestUserRepository

    @Test
    fun test() {
        testUserRepository.save(TestUser(name = "Sagar"))
        val users = testUserRepository.findAll()
        users.forEach { user ->
            println(">>> ${user.id} : ${user.name}")
        }
        assertThat(users.size != 0)
    }

    companion object {
        private val postgresContainer = PostgreSQLContainer("postgres:latest")

        @JvmStatic
        @BeforeAll
        fun beforeAll(): Unit {
            postgresContainer.start()
        }

        @JvmStatic
        @AfterAll
        fun afterAll(): Unit {
            postgresContainer.stop()
        }
    }

    class DataSourceInitializer : ApplicationContextInitializer<ConfigurableApplicationContext?> {
        override fun initialize(applicationContext: ConfigurableApplicationContext) {
            TestPropertySourceUtils.addInlinedPropertiesToEnvironment(
                applicationContext,
                postgresContainer.jdbcUrl,
                postgresContainer.username,
                postgresContainer.password
            )
        }
    }
}