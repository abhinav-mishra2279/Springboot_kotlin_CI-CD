package com.example.demo.service

import com.example.demo.gateway.PaymentGateway
import com.example.demo.model.Order
import org.springframework.stereotype.Service

@Service
class OrderService(private val gateway: PaymentGateway) {

    fun placeOrder(order: Order): String {
        if (order.amount <= 0) throw IllegalArgumentException("Invalid")
        val success = gateway.pay(order.amount)
        return if (success) "ORDER_PLACED" else "PAYMENT_FAILED"
    }
}
