package com.sagar.testprojone

import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/user")
class UserController(private val userService: UserService) {

    @PostMapping
    fun saveUser(@Valid @RequestBody saveUserRequest: SaveUserRequest): TestUser {
        return userService.saveUser(saveUserRequest.name)
    }
}