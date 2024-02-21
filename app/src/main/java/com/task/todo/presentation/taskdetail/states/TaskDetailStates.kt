package com.task.todo.presentation.taskdetail.states

data class TaskDetailStates(
    var description : String = "",
    var completed : Boolean =false,
    var notCompleted : Boolean =false,
    var isLoading : Boolean = false,
    var desError : String? = null,
    var successMsg : String? = null,
    var isDeleted: Boolean = false
)
