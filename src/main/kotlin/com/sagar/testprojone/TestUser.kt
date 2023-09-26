package com.sagar.testprojone

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "test_users")
data class TestUser(
    @Id
    var id: String = UUID.randomUUID().toString(),
    var name: String = ""
)