package com.example.demo.controller

import com.example.demo.model.Order
import com.example.demo.service.OrderService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/orders")
class OrderController(private val service: OrderService) {

    @PostMapping
    fun place(@RequestBody order: Order): String = service.placeOrder(order)
}
