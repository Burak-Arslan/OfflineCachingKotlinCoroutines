package com.example.myapplication.data

import androidx.room.withTransaction
import com.example.myapplication.api.Api
import com.example.myapplication.util.networkBoundResource
import kotlinx.coroutines.delay
import javax.inject.Inject

class RestaurantRepository @Inject constructor(
    private val api: Api,
    private val db: RestaurantsDatabase
){
    private val restaurantsDatabase = db.restaurantDao()

    fun getRestaurants() = networkBoundResource(
        query = {
            restaurantsDatabase.getAllRestaurants()
        },
        fetch = {
            delay(2000)
            api.getRestaurants()
        },
        saveFetchResult = { restaurants ->
            db.withTransaction {
             restaurantsDatabase.deleteAllRestaurants()
             restaurantsDatabase.insertRestaurants(restaurants)
            }
        }
    )
}