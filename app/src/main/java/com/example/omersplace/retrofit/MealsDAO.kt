package com.example.omersplace.retrofit

import com.example.omersplace.entities.CRUDResponse
import com.example.omersplace.entities.CartResponse
import com.example.omersplace.entities.MealResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface MealsDAO {


    @GET("tumYemekleriGetir.php")
    fun getAllMeals(): Call<MealResponse>


    @POST("sepeteYemekEkle.php")
    @FormUrlEncoded
    fun addToCart(@Field("yemek_adi") meal_name: String,
                  @Field("yemek_resim_adi") meal_image_name: String,
                  @Field("yemek_fiyat") meal_price: Int,
                  @Field("yemek_siparis_adet") meal_order_count: Int,
                  @Field("kullanici_adi") username: String): Call<CRUDResponse>


    @POST("sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    fun bringOrdersInCart(@Field("kullanici_adi") username: String): Call<CartResponse>


    @POST("sepettenYemekSil.php")
    @FormUrlEncoded
    fun deleteMealInCart(@Field("sepet_yemek_id") idOfMeal: Int,
                         @Field("kullanici_adi") username: String): Call<CRUDResponse> }