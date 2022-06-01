package com.example.omersplace.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class MealInCart(@SerializedName("sepet_yemek_id") @Expose var meal_cart_id: Int,
                      @SerializedName("yemek_adi") @Expose var meal_name: String,
                      @SerializedName("yemek_resim_adi") @Expose var meal_image: String,
                      @SerializedName("yemek_fiyat") @Expose var meal_price: Double,
                      @SerializedName("yemek_siparis_adet") @Expose var meal_order_count: String,
                      @SerializedName("kullanici_adi") @Expose var username: String): Serializable {

    fun getPrice(): String {
        return this.meal_price.toString()
    }
}