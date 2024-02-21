package com.task.todo.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.task.todo.presentation.addtask.AddTaskScreen
import com.task.todo.presentation.taskdetail.TaskDetailScreen
import com.task.todo.presentation.tasklistview.TaskListScreen


@Composable
fun Navigation() {
    val navController = rememberNavController()
    val refreshData = remember { mutableStateOf(false) }

    NavHost(navController = navController, startDestination = Screens.TaskListScreen.route) {
        composable(route = Screens.TaskListScreen.route) {
            TaskListScreen(navController = navController, refreshData = refreshData)
        }
        composable(route = Screens.TaskDetailScreen.route) {
            TaskDetailScreen(navController = navController) {
                refreshData.value = true
            }
        }
        composable(route = Screens.AddTaskScreen.route) {
            AddTaskScreen(onAddTaskClosed = {
                refreshData.value =true
            })
        }

    }
}


