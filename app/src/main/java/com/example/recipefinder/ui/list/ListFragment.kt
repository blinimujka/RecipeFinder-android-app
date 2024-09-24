package com.example.recipefinder.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipefinder.databinding.FragmentListBinding
import com.example.recipefinder.ui.home.HomeFragment

class ListFragment : Fragment() {

    lateinit var binding: FragmentListBinding
    private val viewModel: ListViewModel by viewModels()
    private val args: ListFragmentArgs by navArgs()
    lateinit var layoutManager: LinearLayoutManager
    private val listAdapter = ListAdapter()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(
            layoutInflater,
            container, false
        )

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()

        when (HomeFragment.locationSelected) {

            "cuisines" -> args.cuisineName?.let {
                activity?.setTitle("${args.cuisineName} cuisine")
                viewModel.getCuisineByName(it)
            }
            "diets" -> args.dietName?.let {
                activity?.setTitle("${args.dietName} diet")
                print(args.dietName)
              viewModel.getDietByName(it)
            }
            "search" -> args.query?.let{
                activity?.setTitle(args.query.toString())
                viewModel.searchByName(it)
                println("test serach")
               println(viewModel.searchByName(it))
            }
        }





        with(binding) {

            layoutManager = LinearLayoutManager(activity)
            listRecyclerView.adapter = listAdapter
            listRecyclerView.layoutManager = layoutManager


        }


    }

    fun observeViewModel() {
        viewModel.foodLiveData.observe(viewLifecycleOwner) {
            listAdapter.foodList = it

        }
    }




}