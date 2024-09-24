package com.example.recipefinder.domain.models

data class Food(
    val id:String,
    val name: String,
    val price: Long,
    val calories: Long,
    val diet: String,
    val cuisine: String,
    val thumbnail:String,


    )

data class FoodImages(
    val id:String,
    val image:String
)

/*val foodList = listOf<Food>(
    Food("Pizza",12,13,"ad","", listOf()),
    Food("Pizza",12,13,"ad","abc",listOf())
)*/
