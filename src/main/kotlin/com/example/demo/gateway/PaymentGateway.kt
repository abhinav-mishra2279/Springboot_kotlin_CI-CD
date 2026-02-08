package com.example.demo.gateway

interface PaymentGateway {
    fun pay(amount: Double): Boolean
}
