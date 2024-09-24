package com.example.recipefinder.ui.list

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.recipefinder.R
import com.example.recipefinder.databinding.BrowseItemsBinding
import com.example.recipefinder.databinding.ListItemRecipeBinding
import com.example.recipefinder.domain.models.Food
import com.example.recipefinder.domain.models.Recipe
import com.example.recipefinder.ui.home.HomeFragment
import com.squareup.picasso.Picasso

class ListAdapter: RecyclerView.Adapter<ListAdapter.ViewHolder>() {
    var foodList: List<Food> = emptyList()

        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ViewHolder(val binding: ListItemRecipeBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.ViewHolder {
        val binding = ListItemRecipeBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)

    }

    override fun getItemCount(): Int = foodList.size

    override fun onBindViewHolder(holder: ListAdapter.ViewHolder, position: Int) {
        val currentFoodItem = foodList[position]

        with(holder.binding){
            foodName.text = currentFoodItem.name
            itemPrice.text = "$ ${currentFoodItem.price.toString()}"

           Picasso.get().load(currentFoodItem.thumbnail).into(foodImage)


                root.setOnClickListener{
                  var recipeID = bundleOf(Pair("recipeID",currentFoodItem.id))
                  holder.itemView.findNavController().navigate(R.id.listToDetail,recipeID)
                }
            }


        }


    }
