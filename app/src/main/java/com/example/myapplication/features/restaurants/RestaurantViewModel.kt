package com.example.myapplication.features.restaurants

import androidx.lifecycle.*
import com.example.myapplication.api.Api
import com.example.myapplication.data.Restaurant
import com.example.myapplication.data.RestaurantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RestaurantViewModel @Inject constructor(repository: RestaurantRepository) : ViewModel() {

    val restaurants = repository.getRestaurants().asLiveData()

}