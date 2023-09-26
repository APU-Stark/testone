package com.sagar.testprojone

import org.springframework.http.client.SimpleClientHttpRequestFactory
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class ExternalServerPingMaster {

    var baseUrl = KWAndConst.BASE_URL

    fun executeCurlRequest(): Any {
        val simpleClientHttpRequestFactory = SimpleClientHttpRequestFactory()
        simpleClientHttpRequestFactory.setReadTimeout(20 * 1000)
        simpleClientHttpRequestFactory.setConnectTimeout(20 * 1000)
        val restTemplateMaster = RestTemplate(simpleClientHttpRequestFactory)
        val curlRequest =
            "${baseUrl}?country=keywordCountryCodeAlpha2&apiKey=7484e80e04454b70bd7cfbee42542364"
        val response = restTemplateMaster.getForEntity(
            curlRequest,
            String::class.java
        )
        return response.body ?: ""
    }
}