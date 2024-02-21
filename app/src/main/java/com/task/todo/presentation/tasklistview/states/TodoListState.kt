package com.task.todo.presentation.tasklistview.states

import com.task.todo.data.remote.dto.Todo
import com.task.todo.domain.model.Todos

data class TodoListState(
    var isLoading: Boolean= false,
    val todoList: Todos,
    val error: String? = "",
    var todo : List<Todo>

)
