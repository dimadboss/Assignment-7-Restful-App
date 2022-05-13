package com.mmcs.todolist.presentation.dish_type_element

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.mmcs.todolist.presentation.components.Colors
import androidx.compose.material.TopAppBar
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.mmcs.todolist.data.network.DishType
import com.mmcs.todolist.data.network.models.DishImage
import com.mmcs.todolist.presentation.components.ItemCard

@Composable
fun DishTypeElementScreen(
    navController: NavController,
    id: DishType,
    viewModel: DishTypeElementViewModel = DishTypeElementViewModel(id)
) {
    val dishImages by viewModel.dishImage.observeAsState()
    val isLoading by viewModel.isLoading.observeAsState(true)
    Scaffold(backgroundColor = Colors.colorPrimary, topBar = {
        TopAppBar(backgroundColor = Colors.colorPrimary, navigationIcon = {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Colors.colorOnPrimary
                )
            }
        }, title = {
            Text(
                text = "Random images",
                fontSize = 16.sp,
                color = Colors.colorOnPrimary
            )
        })
    }) {
        it
        if (isLoading)
            return@Scaffold Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = Colors.colorSecondary)
            }


        dishImages?.let { dishImages ->
            LazyColumn() {
                items(dishImages) { dishImage ->
                    ItemCard(
                        title = {
                            Text(
                                text = buildName(dishImage),
                                modifier = Modifier.padding(
                                    horizontal = 8.dp,
                                    vertical = 16.dp
                                ),
                                fontSize = 16.sp,
                                color = Colors.colorOnPrimary
                            )
                        }) {

                        SubcomposeAsyncImage(
                            modifier = Modifier
                                .size(384.dp)
                                .clip(RoundedCornerShape(40))
                                .fillMaxWidth(),
                            model = dishImage.image,
                            loading = {
                                CircularProgressIndicator(
                                    color = Colors.colorSecondary,
                                    modifier = Modifier
                                        .size(4.dp)
                                        .padding(100.dp)
                                )
                            },
                            contentDescription = ""
                        )
                    }
                }

            }
        }
    }

}

fun buildName(text: DishImage): String {
    val number = text.image.filter { it.isDigit() }
    return "Random ${text.type?.name?.lowercase() ?: ""} image #${number}"
}