package com.example.myapplication.features.restaurants

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.data.Restaurant
import com.example.myapplication.databinding.RestaurantItemBinding

class RestaurantAdapter : ListAdapter<Restaurant, RestaurantAdapter.RestaurantViewHolder>(RestaurantComparator()) {


    class RestaurantViewHolder(private val binding: RestaurantItemBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(restaurant: Restaurant){
            binding.apply {
                Glide.with(itemView)
                    .load(restaurant.logo)
                    .into(imgRestaurant)

                txtAddress.text = restaurant.address
                txtName.text = restaurant.name
                txtType.text = restaurant.type
            }
        }
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val currentItem  = getItem(position)
        if(currentItem != null){
            holder.bind(currentItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val binding = RestaurantItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return  RestaurantViewHolder(binding)
    }

    class RestaurantComparator: DiffUtil.ItemCallback<Restaurant>(){
        override fun areItemsTheSame(oldItem: Restaurant, newItem: Restaurant) = oldItem.name == newItem.name


        override fun areContentsTheSame(oldItem: Restaurant, newItem: Restaurant) =
            oldItem  == newItem

    }
}