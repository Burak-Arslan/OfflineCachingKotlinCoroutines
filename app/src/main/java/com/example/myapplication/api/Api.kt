package com.example.myapplication.api

import com.example.myapplication.data.Restaurant
import retrofit2.http.GET

interface Api {

    companion object {
        const val BASE_URL = "https://random-data-api.com/api/"
    }


    @GET("restaurant/random_restaurant?size=20")
    suspend fun getRestaurants():List<Restaurant>
}