package com.example.recipefinder.ui.checkout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.example.recipefinder.databinding.FragmentHomeBinding
import com.example.recipefinder.databinding.FragmentPurchaseBinding
import com.example.recipefinder.domain.models.CartItem
import com.example.recipefinder.ui.detail.CartBottomSheet
import com.example.recipefinder.ui.detail.DetailFragment
import com.example.recipefinder.ui.list.ListViewModel

class PurchaseFragment:Fragment() {

    lateinit var binding : FragmentPurchaseBinding
    private val viewModel: CheckoutViewModel by viewModels()
    lateinit var purchaseList: List<CartItem>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPurchaseBinding.inflate(
            layoutInflater,
            container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        purchaseList = DetailFragment.appDb.cartDao().getAll()
        binding.purchaseBttn.setOnClickListener {
viewModel.saveOrder(purchaseList)

        }
    }
}