package com.example.omersplace.viewmodels

import android.app.AlertDialog
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.omersplace.entities.Meal
import com.example.omersplace.repo.MealsDAORepo
import kotlin.coroutines.coroutineContext

class HomePageViewModel: ViewModel() {
    val mrepo = MealsDAORepo()
    var mealList: MutableLiveData<List<Meal>>

    init {
        loadMeals()
        mealList = mrepo.mealList
    }

    fun loadMeals(){
        mrepo.getAllMeals()
    }

    fun addToCart(meal: Meal, count: Int){
        mrepo.addToCartAfterDelete(meal, count)

    }

    fun loadOrderList(username: String) {
        mrepo.ordersInCart()
    }

    fun sortByDescending() {
       this.mealList.value = mealList.value!!.sortedWith(compareBy({it.meal_price})).reversed()
    }

    fun sortByAscending() {
        this.mealList.value = mealList.value!!.sortedWith(compareBy({it.meal_price}))
    }

    fun sortByTitle() {
        this.mealList.value = mealList.value!!.sortedWith(compareBy({it.meal_name}))
    }

}