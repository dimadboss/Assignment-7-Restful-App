package com.mmcs.todolist.presentation.dish_type_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mmcs.todolist.data.network.DishType
import com.mmcs.todolist.presentation.components.Colors
import com.mmcs.todolist.presentation.components.ItemCard
import com.mmcs.todolist.presentation.main.AppScreen

@Composable
fun DishTypeListScreen(
    navController: NavController,
) {
    Scaffold(
        backgroundColor = Colors.colorPrimary,
        topBar = {
            TopAppBar(backgroundColor = Colors.colorPrimary) {
                Text(
                    text = "Random dish API",
                    fontSize = 16.sp,
                    color = Colors.colorOnPrimary
                )
            }
        }) {
        it
        LazyColumn() {
            items(DishType.values()) { dishImage ->
                ItemCard(
                    modifier = Modifier.clickable { navController.navigate(AppScreen.DishTypeElementScreen.route + "?id=${dishImage.name}") },
                    title = {
                        Text(
                            text = dishImage.name,
                            modifier = Modifier.padding(
                                horizontal = 8.dp,
                                vertical = 16.dp,
                            ),
                            fontSize = 18.sp,
                            color = Colors.colorOnPrimary
                        )
                    }) {
                }
            }

        }
    }
}
