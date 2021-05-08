package com.example.myapplication.features.restaurants

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel : RestaurantViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val restauratAdapter = RestaurantAdapter()

        binding.apply {
            mainRecycler.apply {
                adapter  = restauratAdapter
                layoutManager = LinearLayoutManager(this@MainActivity)
            }

            viewModel.restaurants.observe(this@MainActivity){ result ->
                restauratAdapter.submitList(result.data)

                loading.isVisible = result is Resource.Loading && result.data.isNullOrEmpty()
                txtError.isVisible = result is Resource.Loading && result.data.isNullOrEmpty()
                txtError.visibility = View.VISIBLE
                txtError.text = result.error?.localizedMessage
            }
        }
    }
}