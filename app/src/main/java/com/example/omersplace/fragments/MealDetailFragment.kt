package com.example.omersplace.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.omersplace.R
import com.example.omersplace.databinding.FragmentMealDetailBinding
import com.example.omersplace.retrofit.ApiUtils
import com.example.omersplace.viewmodels.HomePageViewModel
import com.example.omersplace.viewmodels.MealDetailViewModel
import com.squareup.picasso.Picasso

class MealDetailFragment : Fragment() {
    private lateinit var binding: FragmentMealDetailBinding
    private lateinit var viewModel: MealDetailViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMealDetailBinding.inflate(inflater, container, false)

        val bundle: MealDetailFragmentArgs by navArgs()
        val meal = bundle.meal
        binding.meal = meal

        val image_uri = ApiUtils.IMAGE_URL + meal.meal_image
        Picasso.get().load(image_uri)
            .error(R.mipmap.ic_launcher)
            .into(binding.imageViewMealDetail)

        binding.imageViewAdd.setOnClickListener {
            binding.textViewNumberOfItems.text = (getCountofItems() + 1).toString()
            binding.textViewTotalCost.text = getString(R.string.total_cost) + " " + (getCountofItems() * meal.meal_price).toString() + " " + getString(R.string.currency)
        }

        binding.imageViewRemove.setOnClickListener {
            if(binding.textViewNumberOfItems.text.toString().toInt() > 0) {
                binding.textViewNumberOfItems.text = (getCountofItems() - 1).toString()
                binding.textViewTotalCost.text = getString(R.string.total_cost) + " " + (getCountofItems() * meal.meal_price).toString() + " " + getString(R.string.currency)
            }
        }

        binding.buttonAddToCart.setOnClickListener {
            if (getCountofItems() > 0) {
                viewModel.addToCart(meal, binding.textViewNumberOfItems.text.toString().toInt())
                Toast.makeText(context, resources.getText(R.string.added_to_cart), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, resources.getText(R.string.choose_quantity), Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setHasOptionsMenu(true)
        val tempViewModel: MealDetailViewModel by viewModels()
        viewModel = tempViewModel

    }

    fun getCountofItems(): Int {
        return binding.textViewNumberOfItems.text.toString().toInt()
    }

}