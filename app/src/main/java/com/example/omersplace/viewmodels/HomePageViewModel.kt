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
        var t = ArrayList<Meal>()
        //mealList.value = mealList.value?.sortedBy { }
        var i = mealList.value?.size!! - 1

        while (i >= 0) {
            Log.e("sortByDescending", i.toString())
            t.add(mealList.value!![i])
            Log.e("sortByDescending", mealList.value!![i].toString())
            i--
        }

        //mealList.value = t
        mealList.value = mealList?.value!!.reversed()

    }

}