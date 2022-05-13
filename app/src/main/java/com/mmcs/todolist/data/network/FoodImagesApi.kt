package com.mmcs.todolist.data.network

import com.mmcs.todolist.data.network.models.DishImage
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface FoodImagesApi {
    @GET("images/{food}")
    fun fetchDishImage(@Path("food") food: String): Call<DishImage>
}