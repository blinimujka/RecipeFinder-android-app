package com.example.recipefinder.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipefinder.MainActivity
import com.example.recipefinder.R
import com.example.recipefinder.data.AppDatabase
import com.example.recipefinder.databinding.FragmentDetailBinding
import com.example.recipefinder.domain.models.CartItem
import com.example.recipefinder.ui.list.ListFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class DetailFragment:Fragment() {

    private val args: DetailFragmentArgs by navArgs()
    lateinit var binding: FragmentDetailBinding
    val detailViewModel: DetailViewModel by viewModels()
    lateinit var detailLayout: LinearLayoutManager
    private var similarAdapter = SimilarRecipesAdapter()

    companion object {
        lateinit var appDb : AppDatabase
    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(
            layoutInflater,
            container, false
        )
        appDb = AppDatabase.getDatabase(requireContext())

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailViewModel.getFoodById(args.recipeID.toString())
            observeViewModel()


        println("test similar livedata ${similarAdapter.similarRecipesList}")

        with(binding) {
            detailLayout = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            binding.similarRecipesRecyclerView.adapter = similarAdapter
            binding.similarRecipesRecyclerView.layoutManager = detailLayout




            btnAddToCart.setOnClickListener {
                var id = detailViewModel.detailedFoodLiveData.value?.id
                var name = detailViewModel.detailedFoodLiveData.value?.name
                var price = detailViewModel.detailedFoodLiveData.value?.price
                var thumbnail = detailViewModel.detailedFoodLiveData.value?.thumbnail

                println("$id document id")

                detailViewModel.insert(CartItem(id!!,name,price as Long,thumbnail))

                findNavController().navigate(R.id.detail_to_cart)


            }

            observeViewModel()
        }
    }
    fun observeViewModel() {
        detailViewModel.imgLivedata.observe(viewLifecycleOwner) {

            var w = mutableListOf<String>()

            binding.viewPager.apply {

                val adapter = ViewPagerAdapter(it)
                binding.viewPager.adapter = adapter
            }
        }
       detailViewModel.detailedFoodLiveData.observe(viewLifecycleOwner){
           binding.nameTextView.text = it.name
           binding.caloriesTextView.text = it.calories.toString()
           binding.cuisineTextView.text =it.cuisine
           binding.dietTextView.text = it.diet

           detailViewModel.getSimilar(it.cuisine,it.diet)
       }

        detailViewModel.similarFoodLiveData.observe(viewLifecycleOwner){
            similarAdapter.similarRecipesList = it
        }

        }
    }





