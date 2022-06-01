package com.example.omersplace.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.omersplace.R
import com.example.omersplace.databinding.MealCardBinding
import com.example.omersplace.databinding.MealincartCardBinding
import com.example.omersplace.entities.Meal
import com.example.omersplace.entities.MealInCart
import com.example.omersplace.retrofit.ApiUtils
import com.example.omersplace.viewmodels.CartViewModel
import com.example.omersplace.viewmodels.HomePageViewModel
import com.squareup.picasso.Picasso

class CartAdapter(var mContext: Context,
                  var orderList: List<MealInCart>,
                  var viewModel: CartViewModel): RecyclerView.Adapter<CartAdapter.MealInCartCardHolder>() {

    inner class MealInCartCardHolder(binding: MealincartCardBinding): RecyclerView.ViewHolder(binding.root) {
        var binding: MealincartCardBinding
        init {
            this.binding = binding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealInCartCardHolder {
        val layoutInflater = LayoutInflater.from(mContext)
        val binding: MealincartCardBinding = DataBindingUtil.inflate(layoutInflater, R.layout.mealincart_card, parent, false)
        return MealInCartCardHolder(binding)
    }

    override fun onBindViewHolder(holder: MealInCartCardHolder, position: Int) {
        val meal = orderList.get(position)
        val t = holder.binding

        t.order = meal

        t.cardView.animation = AnimationUtils.loadAnimation(mContext, com.airbnb.lottie.R.anim.abc_grow_fade_in_from_bottom)

        t.textViewTotalPrice.text = (meal.meal_price * meal.meal_order_count.toInt()).toString()

        t.imageViewDelete.setOnClickListener {
            viewModel.deleteMealInCart(meal)
        }

        val image_uri = ApiUtils.IMAGE_URL + meal.meal_image
        Picasso.get().load(image_uri)
            .error(R.mipmap.ic_launcher)
            .into(t.imageViewMealImage)
    }

    override fun getItemCount(): Int {
        return orderList.size
    }

}