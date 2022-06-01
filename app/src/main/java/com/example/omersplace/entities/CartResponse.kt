package com.example.omersplace.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CartResponse(@SerializedName("sepet_yemekler") @Expose var cartList: List<MealInCart>,
                        @SerializedName("success") @Expose var success: Int) {
}