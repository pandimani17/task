package com.task.todo.domain.repository

import com.task.todo.data.remote.dto.Todo

interface TodoRepositoryLocal {

    suspend fun addTodoListLocal(todo:List<Todo>)

    suspend fun getTodoListFromLocal() : List<Todo>

    suspend fun addTodo (todo : Todo)

    suspend fun updateTodo(todo: Todo)

    suspend fun deleteTodo(todo: Todo)

}