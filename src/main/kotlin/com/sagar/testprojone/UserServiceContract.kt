package com.sagar.testprojone

interface UserServiceContract {

    fun saveUser(userName: String): TestUser

    fun getUser(id: String): TestUser?
}