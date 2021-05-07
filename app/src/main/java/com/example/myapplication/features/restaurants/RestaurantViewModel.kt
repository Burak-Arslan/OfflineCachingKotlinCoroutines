package com.example.myapplication.features.restaurants

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.api.Api
import com.example.myapplication.data.Restaurant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RestaurantViewModel @Inject constructor(api: Api) : ViewModel() {
    private val restaurantLiveData = MutableLiveData<List<Restaurant>>()
    val restaurants: LiveData<List<Restaurant>> = restaurantLiveData

    init {
        viewModelScope.launch {
         val restaurants = api.getRestaurants()
         delay(2000)
         restaurantLiveData.value = restaurants
        }
    }
}