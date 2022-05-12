package com.mmcs.todolist.presentation.main

sealed class AppScreen(val route: String) {
    object DishTypeListScreen : AppScreen("dish_type_list_screen")
    object DishTypeElementScreen : AppScreen("dish_type_element_screen")
}

