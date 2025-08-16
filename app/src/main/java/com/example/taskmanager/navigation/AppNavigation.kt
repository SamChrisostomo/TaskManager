package com.example.taskmanager.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation(){
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ApplicationRoutes.HOME_ROUTE
    ){
        composable(route = ApplicationRoutes.HOME_ROUTE) {

        }
        composable(route = ApplicationRoutes.ADD_ROUTE) {

        }
        composable(route = ApplicationRoutes.EDIT_ROUTE) {

        }
    }
}