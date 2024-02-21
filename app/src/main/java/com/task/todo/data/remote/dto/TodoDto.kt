package com.task.todo.data.remote.dto

import com.task.todo.domain.model.Todos

data class TodoDto(
    val limit: Int,
    val skip: Int,
    val todos: List<Todo>,
    val total: Int
)


fun TodoDto.toTodos(): Todos {
    return Todos(
        limit = limit,
        skip = skip,
        todos = todos,
        total = total
    )
}