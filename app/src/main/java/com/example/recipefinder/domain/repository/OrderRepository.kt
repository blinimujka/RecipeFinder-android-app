package com.example.recipefinder.domain.repository

import android.content.ContentValues.TAG
import android.util.Log
import com.example.recipefinder.domain.models.CartItem
import com.example.recipefinder.domain.models.Food
import com.example.recipefinder.ui.launch.AuthViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class OrderRepository {
    private var allItems: ArrayList<String> = arrayListOf()
    private var price: Long = 0
    private val orderCollection = Firebase.firestore.collection("orders")
    private lateinit var auth: AuthViewModel

    fun addOrder(list: List<CartItem>) {

        for (item in list) {
            allItems.add(item.id)
            price += item.price
        }
        val order = hashMapOf(
            "totalPrice" to price,
            "foodID" to allItems,
            "user" to AuthViewModel.currentUser
        )

        orderCollection.document().set(order)
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }

    }
}