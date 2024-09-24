package com.example.recipefinder.ui.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.recipefinder.databinding.BrowseItemsBinding
import com.example.recipefinder.databinding.CartItemsBinding
import com.example.recipefinder.databinding.SimilarRecipesBinding
import com.example.recipefinder.domain.models.dietsList
class   CartItemAdapter: RecyclerView.Adapter<CartItemAdapter.ViewHolder>() {

    class ViewHolder(val binding: CartItemsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemAdapter.ViewHolder {
        val binding = CartItemsBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return CartItemAdapter.ViewHolder(binding)
    }


    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: CartItemAdapter.ViewHolder, position: Int) {
        val currentItem = dietsList[position]

        with(holder.binding) {
            //cartItemName.text = currentItem.name
            //Picasso.get().load(currentItem.ImageUrl).into(cartItemImg)

            root.setOnClickListener {

            }
        }
    }

}
