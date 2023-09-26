package com.sagar.testprojone

import mockwebserver3.MockResponse
import mockwebserver3.MockWebServer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ExternalServerPingMasterTest {

    private lateinit var mockWebServer: MockWebServer

    private lateinit var externalServerPingMaster: ExternalServerPingMaster

    @BeforeEach
    fun beforeEach() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
        externalServerPingMaster = ExternalServerPingMaster()
        externalServerPingMaster.baseUrl = mockWebServer.url("/").toUrl().toString()
    }

    @AfterEach
    fun afterEach() {
        mockWebServer.shutdown()
    }

    @Test
    fun testOne() {
        mockWebServer.enqueue(
            MockResponse().setResponseCode(200)
                .setBody("this is a response")
        )

        val response = externalServerPingMaster.executeCurlRequest()
        println(response)
    }
}