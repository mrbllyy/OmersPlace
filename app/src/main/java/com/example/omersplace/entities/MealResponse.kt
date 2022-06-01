package com.example.omersplace.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MealResponse(@SerializedName("yemekler") @Expose var mealList: List<Meal>,
                        @SerializedName("success") @Expose var success: Int) {
}