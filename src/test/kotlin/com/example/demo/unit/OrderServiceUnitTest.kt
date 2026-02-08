package com.example.demo.unit

import com.example.demo.gateway.PaymentGateway
import com.example.demo.model.Order
import com.example.demo.service.OrderService
import io.mockk.called
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith


class OrderServiceUnitTest {

    private val gateway = mockk<PaymentGateway>()
    private val service = OrderService(gateway)

    @Test
    fun `order is placed successfully`(){
        every { gateway.pay(any()) } returns true

        val result = service.placeOrder(Order(1, 100.0))
        assertEquals("ORDER_PLACED", result)
        verify { gateway.pay(100.0) }
    }

    @Test
    fun `invalid order throws exception`(){
        assertFailsWith<IllegalArgumentException> {
            service.placeOrder(Order(2, -10.0))
        }

        verify { gateway wasNot called }
    }
}