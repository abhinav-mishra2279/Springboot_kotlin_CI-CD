package com.example.demo.integration

import com.example.demo.service.OrderService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc
class OrderIntegrationTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var service: OrderService

    @Test
    fun `integration - successful test`(){
        mockMvc.post("/orders"){
            contentType = MediaType.APPLICATION_JSON
            content = """
                {"id":1, "amount":200}
            """.trimIndent()
        }.andExpect { status { isOk() } }
    }

    @Test
    fun `integration - invalid test`(){
        mockMvc.post("/orders"){
            contentType = MediaType.APPLICATION_JSON
            content = """
                {"id":1, "amount":0}
            """.trimIndent()
        }.andExpect { status { isBadRequest() } }
    }
}