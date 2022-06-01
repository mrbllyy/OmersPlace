package com.example.omersplace.repo

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.example.omersplace.databinding.FragmentCartBinding
import com.example.omersplace.entities.*
import com.example.omersplace.fragments.CartFragment
import com.example.omersplace.retrofit.ApiUtils
import com.example.omersplace.retrofit.MealsDAO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.coroutineContext

class MealsDAORepo {
    var mealList: MutableLiveData<List<Meal>>
    var cartList: MutableLiveData<List<MealInCart>>
    var mealDAO: MealsDAO


    init {
        mealList = MutableLiveData()
        cartList = MutableLiveData()
        mealDAO = ApiUtils.getMealsDAOInterface()
    }

    //tumYemekleriGetir.php
    fun getAllMeals(){
        val success = 0
        mealDAO.getAllMeals().enqueue(object : Callback<MealResponse> {
            override fun onResponse(call: Call<MealResponse>?, response: Response<MealResponse>?) {
                val list = response?.body()?.mealList
                val success = response?.body()?.success

                Log.e("getAllMeals", " $success - $list")

                mealList.value = list
            }

            override fun onFailure(call: Call<MealResponse>?, t: Throwable?) {
                Log.e("getAllMeals", "FAIL")
            }
        })

    }

    //sepeteYemekEkle.php
    fun addToCart(meal: Meal, count: Int){
        //username will be OmersPlace
        val username = "OmersPlace"

        mealDAO.addToCart(meal.meal_name, meal.meal_image, meal.meal_price.toInt(), count, username).enqueue(object: Callback<CRUDResponse> {

            override fun onResponse(call: Call<CRUDResponse>?, response: Response<CRUDResponse>?) {
                val success = response?.body()?.success
                val msg = response?.body()?.message
                Log.e("addToCart", "$success - $msg - $meal")

                ordersInCart(username)
            }

            override fun onFailure(call: Call<CRUDResponse>?, t: Throwable?) {
                Log.e("addToCart", "FAIL - $meal")
            }
        })
    }

    //sepeteYemekEkle.php
    fun addToCartAfterDelete(meal: Meal, count: Int){
        //username will be OmersPlace
        val username = "OmersPlace"

        //getAllMeals first to check returned list from service
        var newCount = count

        mealDAO.bringOrdersInCart(username).enqueue(object: Callback<CartResponse> {

            override fun onResponse(call: Call<CartResponse>?, response: Response<CartResponse>?) {
                val success = response?.body()?.success
                val msg = response?.body()?.cartList

                val list = response?.body()?.cartList
                cartList.value = list

                var isThereInCart = false

                if (list != null) {
                    for (m in list) {
                        if(m.meal_name.equals(meal.meal_name)) {
                            isThereInCart = true
                            newCount += m.meal_order_count.toInt()
                            //first delete m in cart
                            mealDAO.deleteMealInCart(m.meal_cart_id, username).enqueue(object: Callback<CRUDResponse>{
                                override fun onResponse(call: Call<CRUDResponse>?, response: Response<CRUDResponse>?) {
                                    val success = response?.body()?.success
                                    val msg = response?.body()?.message

                                    //then add count of m to count to add to cart again
                                    addToCart(meal, newCount)

                                    Log.e("deleteMealInCart", "$success - $msg")
                                }

                                override fun onFailure(call: Call<CRUDResponse>?, t: Throwable?) {
                                    Log.e("deleteMealInCart", "FAIL - $m")
                                }
                            })

                            break
                        }
                    }
                }

                if (!isThereInCart) {
                    //if the meal is not in the cart, add it directly without deletion
                    addToCart(meal, newCount)
                }

                Log.e("ordersInCart", "$success - $msg")
            }

            override fun onFailure(call: Call<CartResponse>?, t: Throwable?) {
                Log.e("addToCartAfterDelete", "FAIL")

                //which means when the cart is empty, it fails to add it into cart
                //so here, run only addtocart again.

                addToCart(meal, count)

            }

        })

    }

    //sepettekiYemekleriGetir.php
    fun ordersInCart(username: String){
        //username will be OmersPlace

        mealDAO.bringOrdersInCart(username).enqueue(object: Callback<CartResponse> {

            override fun onResponse(call: Call<CartResponse>?, response: Response<CartResponse>?) {
                val success = response?.body()?.success
                val msg = response?.body()?.cartList

                val liste = response?.body()?.cartList

                if (cartList.value != liste) {
                    cartList.value = liste
                }

                Log.e("ordersInCart", "$success - $msg")
            }

            override fun onFailure(call: Call<CartResponse>?, t: Throwable?) {
                //If cart is empty, it will executed. And we should change cartlist also.
                Log.e("ordersInCart", "FAIL")
                Log.e("ordersInCart", "CART IS EMPTY!")

                //set cartlist as empty
                cartList.value = ArrayList<MealInCart>()




            }

        })

    }

    //sepettenYemekSil.php
    fun deleteMealInCart(mealInCart: MealInCart){
        //username will be OmersPlace
        val username = "OmersPlace"

        mealDAO.deleteMealInCart(mealInCart.meal_cart_id, username).enqueue(object: Callback<CRUDResponse>{
            override fun onResponse(call: Call<CRUDResponse>?, response: Response<CRUDResponse>?) {
                val success = response?.body()?.success
                val msg = response?.body()?.message

                ordersInCart(username)

                Log.e("deleteMealInCart", "$success - $msg")
            }

            override fun onFailure(call: Call<CRUDResponse>?, t: Throwable?) {
                Log.e("deleteMealInCart", "FAIL - $mealInCart")
            }
        })
    }
}