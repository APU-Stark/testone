package com.sagar.testprojone

import org.springframework.data.jpa.repository.JpaRepository

interface TestUserRepository : JpaRepository<TestUser, String>