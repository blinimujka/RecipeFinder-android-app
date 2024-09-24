package com.example.recipefinder.ui.detail


import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipefinder.data.AppDatabase
import com.example.recipefinder.domain.models.*
import com.example.recipefinder.domain.repository.FoodRepository
import kotlinx.coroutines.launch


class DetailViewModel: ViewModel() {

    private val foodRepository = FoodRepository()
    var detailedRecipeData = MutableLiveData<DetailedRecipe>()
    var similarFoodLiveData = MutableLiveData<List<Food>>()
    var similarFoodList = ArrayList<Food>()
    val detailedFoodLiveData = MutableLiveData<Food>()
    val imgLivedata = MutableLiveData<MutableList<String>>()

    val cartItemLiveData = MutableLiveData<List<CartItem>>()//nashta duhet me qit ne CartViewModel


    fun getFoodById(food_id: String) {
        foodRepository.getFoodById(food_id).addOnSuccessListener { result ->

            detailedFoodLiveData.value = Food(
                "${result.id}",
                "${result["name"]}",
                result["price"] as Long,
                result["calories"] as Long,
                "${result["diet"]}",
                "${result["cuisine"]}",
                "${result["thumbnail"]}",


                )
            imgLivedata.value = result["image"] as MutableList<String>?

        }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents.", exception)

            }
    }

    fun getAll() {
        viewModelScope.launch {
            cartItemLiveData.value = DetailFragment.appDb.cartDao().getAll()
        }
    }

    fun insert(cartItem: CartItem) {
        viewModelScope.launch {
            DetailFragment.appDb.cartDao().insert(cartItem)
        }
    }

    fun getSimilar(cuisine: String, diet: String) {

        foodRepository.getByCuisine(cuisine).addSnapshotListener { value, error ->
            if (value != null) {
                for(i in value){
                    similarFoodList.add(
                    Food(
                        "${i.id}",
                        "${i.data["name"]}",
                        i.data["price"] as Long,
                        i.data["calories"] as Long,
                        "${i.data["diet"]}",
                        "${i.data["cuisine"]}",
                        "${i.data["thumbnail"]}",
                        )
                    )
                }
                similarFoodLiveData.value=similarFoodList.distinct()
            }
        }
        foodRepository.getByDiet(diet).addSnapshotListener { value, error ->
            if (value != null) {
                for(i in value){
                    similarFoodList.add(
                        Food(
                            "${i.id}",
                            "${i.data["name"]}",
                            i.data["price"] as Long,
                            i.data["calories"] as Long,
                            "${i.data["diet"]}",
                            "${i.data["cuisine"]}",
                            "${i.data["thumbnail"]}",
                        )
                    )
                }
                similarFoodLiveData.value=similarFoodList.distinct()
            }
        }

    }
}


