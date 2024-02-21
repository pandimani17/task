package com.task.todo.data.remote.dto

data class Todo(
    val completed: Boolean,
    val id: Int,
    val todo: String,
    val userId: Int
)