package com.example.omersplace.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.omersplace.entities.Meal
import com.example.omersplace.repo.MealsDAORepo

class MealDetailViewModel: ViewModel() {
    val mrepo = MealsDAORepo()
    //var mealList = MutableLiveData<List<Meal>>()

    fun addToCart(meal: Meal, count: Int){
        //username = OmersPlace
        mrepo.addToCartAfterDelete(meal, count)

    }

}