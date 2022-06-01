package com.example.omersplace.adapters

import android.app.AlertDialog
import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.get
import androidx.core.view.iterator
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.omersplace.R
import com.example.omersplace.databinding.MealCardBinding
import com.example.omersplace.entities.Meal
import com.example.omersplace.fragments.HomePageFragmentDirections
import com.example.omersplace.retrofit.ApiUtils
import com.example.omersplace.viewmodels.HomePageViewModel
import com.squareup.picasso.Picasso


class MealAdapter(var mContext: Context,
                  var mealList: List<Meal>,
                  var viewModel: HomePageViewModel): RecyclerView.Adapter<MealAdapter.MealCardHolder>() {
    inner class MealCardHolder(binding: MealCardBinding): RecyclerView.ViewHolder(binding.root) {
        var binding: MealCardBinding
        init {
            this.binding = binding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealCardHolder {
        val layoutInflater = LayoutInflater.from(mContext)
        val binding: MealCardBinding = DataBindingUtil.inflate(layoutInflater, R.layout.meal_card, parent, false)
        return MealCardHolder(binding)
    }

    override fun onBindViewHolder(holder: MealCardHolder, position: Int) {
        val meal = mealList.get(position)
        val t = holder.binding

        t.meal = meal

        t.cardView.animation = AnimationUtils.loadAnimation(mContext, com.airbnb.lottie.R.anim.abc_grow_fade_in_from_bottom)

        t.imageViewMealImage.setImageResource(R.drawable.ic_icon)

        t.cardView.setOnClickListener {
            //Toast.makeText(mContext, "${meal.meal_name}", Toast.LENGTH_SHORT).show()
            val navDirections = HomePageFragmentDirections.actionHomePageFragmentToMealDetailFragment(meal = meal)
            Navigation.findNavController(it).navigate(navDirections)

        }

        t.buttonAddToCartCardView.setOnClickListener {
            //Add 1 meal into the cart
            viewModel.addToCart(meal, 1)
            viewModel.loadOrderList("OmersPlace")
            Toast.makeText(mContext, mContext.resources.getText(R.string.added_to_cart), Toast.LENGTH_SHORT).show()
        }

        val image_uri = ApiUtils.IMAGE_URL + meal.meal_image
        Picasso.get().load(image_uri)
            .error(R.mipmap.ic_launcher)
            .into(t.imageViewMealImage)

    }

    override fun getItemCount(): Int {
        return mealList.size
    }
}