package com.example.taskmanager.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.taskmanager.ui.homescreen.HomeScreenRoute

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ApplicationRoutes.HOME_ROUTE
    ) {
        composable(route = ApplicationRoutes.HOME_ROUTE) {
            HomeScreenRoute(navController = navController)
        }
//        composable(route = ApplicationRoutes.ADD_ROUTE) {
//
//        }
//        composable(
//            route = ApplicationRoutes.EDIT_ROUTE_TEMPLATE,
//            arguments = listOf(
//                navArgument(
//                    ApplicationRoutes.TASK_ID_ARG
//                ) { type = NavType.IntType })
//        ) { backStackEntry ->
//            val taskId = backStackEntry.arguments?.getInt(ApplicationRoutes.TASK_ID_ARG)
//
//        }
    }
}