package com.example.omersplace.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.omersplace.entities.MealInCart
import com.example.omersplace.repo.MealsDAORepo

class CartViewModel: ViewModel() {

    val mrepo = MealsDAORepo()


    var cartList: MutableLiveData<List<MealInCart>>

    val username = "OmersPlace"

    init {
        //loadOrderList(username)
        cartList = mrepo.cartList
    }

    fun loadOrderList(username: String) {
        mrepo.ordersInCart(username)
    }

    fun deleteMealInCart(mealInCart: MealInCart) {
        mrepo.deleteMealInCart(mealInCart)

    }

}