package com.example.recipefinder.domain.repository

import android.content.ContentValues
import android.util.Log
import com.example.recipefinder.domain.models.Food
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FoodRepository {


    val foodCollection = Firebase.firestore.collection("food")



    fun getFoodByCategory(diet: String) {
        foodCollection.whereEqualTo("diet", diet).get()
    }

    fun getFoodById(id:String) =
        foodCollection.document(id)
            .get()

    fun searchByName(query:String) =
        foodCollection.whereEqualTo("name",query)

    fun getByCuisine(cuisine_name:String) =
        foodCollection.whereEqualTo("cuisine",cuisine_name)


    fun getByDiet(diet_name:String) =
        foodCollection.whereEqualTo("diet",diet_name)

    fun insert(food:Food){
        foodCollection.add(food)
    }

     fun readAll(){
        foodCollection
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    var name = document["name"]
                    Log.d(ContentValues.TAG, "abc ${document.id} => ${document["name"]}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents.", exception)
            }
    }

}