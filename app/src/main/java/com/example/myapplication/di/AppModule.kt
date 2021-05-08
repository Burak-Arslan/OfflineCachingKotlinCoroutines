package com.example.myapplication.di

import android.app.Application
import androidx.room.Room
import com.example.myapplication.api.Api
import com.example.myapplication.data.RestaurantsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(Api.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideRestaurantApi(retrofit: Retrofit):Api = retrofit.create(Api::class.java)


    @Provides
    @Singleton
    fun provideDatabase(app: Application) : RestaurantsDatabase =
        Room.databaseBuilder(app, RestaurantsDatabase::class.java,"restaurant_database").build()
}