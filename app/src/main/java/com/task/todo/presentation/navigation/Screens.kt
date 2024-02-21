package com.task.todo.presentation.navigation

sealed class Screens(val route: String) {
    object TaskListScreen : Screens(route = "taskListScreen")
    object TaskDetailScreen : Screens("taskDetailScreen")
    object DeleteTaskScreen : Screens("deleteTaskScreen")
    object UpdateTaskScreen : Screens("updateTaskScreen")
    object AddTaskScreen : Screens ("addTaskScreen")
}
