package com.example.demo.gateway

import org.springframework.stereotype.Component

@Component
class DummyPaymentGateway : PaymentGateway {
    override fun pay(amount: Double): Boolean = true
}
