package com.example.taskmanager.navigation

object ApplicationRoutes {
    //Rota para a Home
    const val HOME_ROUTE = "home"
    //Rota para criar uma nova tarefa
    const val ADD_ROUTE = "addTask"

    //Rota para tela de edição
    const val EDIT_ROUTE_BASE = "editTask"
    const val TASK_ID_ARG = "taskId"
    //Essa variável será usada no NavHost
    const val EDIT_ROUTE_TEMPLATE = "$EDIT_ROUTE_BASE/${TASK_ID_ARG}"

    fun buildTaskRoute(taskId: Int) = "$EDIT_ROUTE_BASE/${taskId}"
}