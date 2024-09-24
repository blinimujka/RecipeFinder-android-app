
package com.example.recipefinder.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat.recreate
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.recipefinder.R
import com.example.recipefinder.data.AppDatabase
import com.example.recipefinder.databinding.BottomsheetCartBinding
import com.example.recipefinder.databinding.CartItemsBinding
import com.example.recipefinder.domain.models.CartItem
import com.example.recipefinder.domain.repository.CartDao
import com.example.recipefinder.ui.checkout.CheckoutViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.squareup.picasso.Picasso
import okhttp3.internal.notifyAll

class CartBottomSheet: BottomSheetDialogFragment() {


    lateinit var binding: BottomsheetCartBinding
    private val viewModel: CheckoutViewModel by viewModels()
    lateinit var cartList: List<CartItem>
    private var totalPrice :Long = 0
    lateinit var purchaseList: List<CartItem>



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BottomsheetCartBinding.inflate(inflater, container, false)

        cartList = emptyList()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getCartItems()
binding.totalPrice.text = "$ ${totalPrice}"

        binding.proceedToCheckout.setOnClickListener {
            purchaseList = DetailFragment.appDb.cartDao().getAll()
            DetailFragment.appDb.cartDao().deleteAll()
            viewModel.saveOrder(purchaseList)
dismiss()
        }
    }


private fun getCartItems(){
       cartList = emptyList()
       cartList = DetailFragment.appDb.cartDao().getAll()
       setupCart(cartList)
    println(cartList)
    println("cartList:^^")

}
private fun removeItem(item:CartItem){
    DetailFragment.appDb.cartDao().delete(item)
    cartList = DetailFragment.appDb.cartDao().getAll()
    binding.cartItemHolder.removeAllViews()
    binding.totalPrice.text = "$ 0"
    setupCart(cartList)

}
    private fun setupCart(cartItem: List<CartItem>) {

        totalPrice = 0

         for (item in cartList) {
            val itemBinding = CartItemsBinding.inflate(layoutInflater, binding.root, false)
            itemBinding.cartItemName.text = item.itemName
            itemBinding.cartItemPrice.text = "$ ${item.price}"
            Picasso.get().load(item.image).into(itemBinding.cartItemImg)
            totalPrice+=item.price

             itemBinding.cancelItem.setOnClickListener {
                 removeItem(item)
             }

            binding.cartItemHolder.addView(itemBinding.root)
             binding.totalPrice.text = "$ ${totalPrice}"
        }
    }

}
