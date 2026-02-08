package com.example.demo.slice

import com.example.demo.controller.OrderController
import com.example.demo.exception.GlobalExceptionHandler
import com.example.demo.service.OrderService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import kotlin.test.Test

@WebMvcTest(OrderController::class)
@Import(GlobalExceptionHandler::class)
class OrderControllerSliceTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @MockkBean
    lateinit var service: OrderService

    @Test
    fun `controller returns 200`(){
        every { service.placeOrder(any()) } returns "ORDER_PLACED"
        mockMvc.post("/orders"){
            contentType= MediaType.APPLICATION_JSON
            content = """
                {"id":1, "amount":200.0}
            """.trimIndent()
        }.andExpect { status { isOk() } }
    }

    @Test
    fun `controller returns 400 for invalid order`(){
        every { service.placeOrder(any()) } throws IllegalArgumentException("Invalid")
        mockMvc.post("/orders"){
            contentType= MediaType.APPLICATION_JSON
            content = """
                {"id":1, "amount":-200.0}
            """.trimIndent()
        }.andExpect {
            status { isBadRequest() }
            content { string("Invalid") }
        }
    }
}