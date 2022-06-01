package com.example.omersplace.fragments

import android.os.Bundle
import android.util.AndroidException
import android.util.AndroidRuntimeException
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.omersplace.adapters.MealAdapter
import com.example.omersplace.databinding.FragmentHomePageBinding
import com.example.omersplace.viewmodels.HomePageViewModel

class HomePageFragment : Fragment() {
    private lateinit var binding: FragmentHomePageBinding
    private lateinit var viewModel: HomePageViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomePageBinding.inflate(inflater, container, false)
        binding.rv.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        viewModel.mealList.observe(viewLifecycleOwner){
            val adapter = MealAdapter(requireContext(), it, viewModel)
            binding.mealAdapter = adapter
        }


        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        val tempViewModel: HomePageViewModel by viewModels()
        viewModel = tempViewModel
    }

}