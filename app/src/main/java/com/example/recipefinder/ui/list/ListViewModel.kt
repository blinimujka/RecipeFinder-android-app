package com.example.recipefinder.ui.list

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipefinder.domain.models.Food
import com.example.recipefinder.domain.repository.FoodRepository
import kotlinx.coroutines.launch

class ListViewModel: ViewModel() {


        private val foodRepository = FoodRepository()
    var foodList = ArrayList<Food>()
    val foodLiveData = MutableLiveData<List<Food>>()

    fun getCuisineByName(cuisine_name: String) {
        viewModelScope.launch {
            foodRepository.getByCuisine(cuisine_name).get().addOnSuccessListener { result ->
                Log.d(TAG, "test before loop")
                foodList.clear()
                for (food in result.documents) {

                    Log.d(TAG, "${food.id} => ${food.data}")
                    foodList.add(
                        Food("${food.id}",
                            "${food["name"]}",
                            food["price"]as Long,
                            food["calories"] as Long,
                            "${food["diet"]}",
                            "${food["cuisine"]}",
                            "${food["thumbnail"]}"
                        )
                    )
                }

                foodLiveData.value = foodList
            }
        }
    }


    fun getDietByName(diet_name: String) {
        viewModelScope.launch {

            foodRepository.getByDiet(diet_name).get().addOnSuccessListener { result ->
                Log.d(TAG, "test before loop121")
                foodList.clear()
                for (food in result.documents) {

                    Log.d(TAG, "${food.id} => ${food.data}")
                    foodList.add(
                        Food("${food.id}",
                            "${food["name"]}",
                            food["price"] as Long,
                            food["calories"] as Long,
                            "${food["diet"]}",
                            "${food["cuisine"]}",
                            "${food["thumbnail"]}"
                        )
                    )
                }
                foodLiveData.value = foodList
            }
        }
    }

            fun searchByName(query: String) {

                foodRepository.searchByName(query).get().addOnSuccessListener { result ->
                    foodList.clear()
                    for (food in result.documents) {

                        Log.d(TAG, "${food.id} => ${food.data}")
                        foodList.add(
                            Food("${food.id}",
                                "${food["name"]}",
                                food["price"] as Long,
                                food["calories"] as Long,
                                "${food["diet"]}",
                                "${food["cuisine"]}",
                                "${food["thumbnail"]}"
                            )
                        )
                    }

                    foodLiveData.value = foodList
                }
            }
        }


