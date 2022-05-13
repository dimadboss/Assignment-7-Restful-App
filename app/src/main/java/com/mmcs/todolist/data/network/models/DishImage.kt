package com.mmcs.todolist.data.network.models

import com.mmcs.todolist.data.network.DishType

data class DishImage(
    val type: DishType?,
    val image: String
)