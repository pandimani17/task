package com.task.todo.domain.repository

import com.task.todo.data.remote.dto.Todo
import com.task.todo.data.remote.dto.TodoDelete
import com.task.todo.data.remote.dto.TodoDto

interface TodoRepository {

    suspend fun getTodoList(limit: Int,skip : Int) : TodoDto

    suspend fun postTodo(body : Todo) : Todo

    suspend fun updateTodo(id : Todo) : Todo

    suspend fun deleteTodo(id: Int) : TodoDelete

}