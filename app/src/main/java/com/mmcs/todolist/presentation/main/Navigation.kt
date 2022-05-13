package com.mmcs.todolist.presentation.main

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.mmcs.todolist.data.network.DishType
import com.mmcs.todolist.presentation.dish_type_element.DishTypeElementScreen
import com.mmcs.todolist.presentation.dish_type_list.DishTypeListScreen


@ExperimentalAnimationApi
private fun slideInAnimation(): (AnimatedContentScope<NavBackStackEntry>.() -> EnterTransition?) =
    { slideIntoContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(1000)) }

@ExperimentalAnimationApi
private fun slideOutAnimation(): (AnimatedContentScope<NavBackStackEntry>.() -> ExitTransition?) =
    { slideOutOfContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(1000)) }

@ExperimentalPagerApi
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Composable
fun Navigation(
    navController: NavHostController,
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = AppScreen.DishTypeListScreen.route
    ) {
        composable(
            route = AppScreen.DishTypeListScreen.route,
            enterTransition = slideInAnimation(),
            exitTransition = slideOutAnimation()
        ) {
            DishTypeListScreen(
                navController = navController,
            )
        }
        composable(
            route = AppScreen.DishTypeElementScreen.route + "?id={id}",
            enterTransition = slideInAnimation(),
            exitTransition = slideOutAnimation(),
            arguments = listOf(navArgument(name = "id") {
                type = NavType.inferFromValueType(DishType.values()[0])
                defaultValue = DishType.values()[0]
            })
        ) {
            val id = it.arguments?.getSerializable("id") ?: ""
            DishTypeElementScreen(navController = navController, id = id as DishType)
        }
    }
}

