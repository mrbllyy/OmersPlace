package com.example.omersplace.retrofit

class ApiUtils {
    companion object {
        val BASE_URL = "http://kasimadalan.pe.hu/yemekler/"
        val IMAGE_URL = "http://kasimadalan.pe.hu/yemekler/resimler/"

        fun getMealsDAOInterface(): MealsDAO {
            return RetrofitClient.getClient(BASE_URL).create(MealsDAO::class.java)
        }
    }
}