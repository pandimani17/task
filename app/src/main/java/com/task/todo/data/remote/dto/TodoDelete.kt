package com.task.todo.data.remote.dto

data class TodoDelete(
    val completed: Boolean,
    val id: Int,
    val todo: String,
    val userId: Int,
    val isDeleted : Boolean
)
