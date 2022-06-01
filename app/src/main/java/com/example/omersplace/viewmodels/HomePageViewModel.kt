package com.example.omersplace.viewmodels

import android.app.AlertDialog
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
        mrepo.ordersInCart(username)
    }



    /*
    fun ara(aramaKelimesi:String){
        krepo.kisiAra(aramaKelimesi)
    }

    fun sil(kisi_id:Int){
        krepo.kisiSil(kisi_id)
    }*/
}