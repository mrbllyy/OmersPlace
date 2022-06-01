package com.example.omersplace.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.omersplace.adapters.CartAdapter
import com.example.omersplace.databinding.FragmentCartBinding
import com.example.omersplace.entities.MealInCart
import com.example.omersplace.viewmodels.CartViewModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CartFragment : Fragment() {
    private lateinit var binding: FragmentCartBinding
    private lateinit var viewModel: CartViewModel

    var listOfMealinCart = ArrayList<MealInCart>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        binding.rv.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)

        viewModel.cartList.observe(viewLifecycleOwner){
            val adapter = CartAdapter(requireContext(), it, viewModel)
            binding.adapter = adapter

            checkVisibility()
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
        val tempViewModel: CartViewModel by viewModels()
        viewModel = tempViewModel


    }

    override fun onResume() {
        super.onResume()

        viewModel.loadOrderList("OmersPlace")
    }


    fun checkVisibility() {
        if (viewModel.cartList.value.isNullOrEmpty()) {
            binding.lottieAnimation.visibility = View.VISIBLE
            binding.textViewNoItemWarning.visibility = View.VISIBLE
        } else {
            binding.lottieAnimation.visibility = View.INVISIBLE
            binding.textViewNoItemWarning.visibility = View.INVISIBLE
        }
    }

}