package com.task.todo.presentation.addtask.states

data class AddTaskState(
    var todo : String = "",
    var completed : Boolean = false,
    var userId : Int = 0,
    var isLoading : Boolean = false,
    var desError : String? = null,
    var successMsg : String? = null
)