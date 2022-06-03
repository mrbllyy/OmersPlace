package com.example.omersplace.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Meal(@SerializedName("yemek_id") @Expose var meal_id: Int,
                @SerializedName("yemek_adi") @Expose var meal_name: String,
                @SerializedName("yemek_resim_adi") @Expose var meal_image: String,
                @SerializedName("yemek_fiyat") @Expose var meal_price: Double): Serializable {

    fun getPrice(): String {
        return this.meal_price.toString()
    }

}