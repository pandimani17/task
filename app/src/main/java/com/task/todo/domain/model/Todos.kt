package com.task.todo.domain.model

import androidx.compose.runtime.MutableState
import com.task.todo.data.remote.dto.Todo

data class Todos(
    val limit: Int= 0,
    val skip: Int = 0,
    var todos:List<Todo> = emptyList(),
    val total: Int = 0

)