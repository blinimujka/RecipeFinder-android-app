package com.example.recipefinder.ui.checkout

import androidx.lifecycle.ViewModel
import com.example.recipefinder.domain.models.CartItem
import com.example.recipefinder.domain.repository.FoodRepository
import com.example.recipefinder.domain.repository.OrderRepository

class CheckoutViewModel:ViewModel() {

    private val orderRepository:OrderRepository=OrderRepository()

    fun saveOrder(list:List<CartItem>){
        orderRepository.addOrder(list)
    }
}