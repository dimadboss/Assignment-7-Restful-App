package com.mmcs.todolist.data.network

import com.mmcs.todolist.data.network.models.DishImage


class DishTypeRepository {
    private var api: ClientApi = ClientApi()

    suspend fun fetchDishImage(id: String): DishImage? = catching { api.fetchDishImage(id) }
}