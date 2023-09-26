package com.sagar.testprojone

import org.springframework.stereotype.Service

@Service
class UserService(
    var testUserRepository: TestUserRepository,
    val externalServerPingMaster: ExternalServerPingMaster
) : UserServiceContract {

    override fun saveUser(userName: String): TestUser {
        return testUserRepository.save(TestUser(name = userName))
    }

    override fun getUser(id: String): TestUser? {
        testUserRepository.findById(id).let { result ->
            if (result.isPresent)
                return result.get()
            else
                return null
        }
    }
}