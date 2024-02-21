package com.task.todo.data.repositoryImpl

import com.task.todo.data.remote.ApiService
import com.task.todo.data.remote.dto.Todo
import com.task.todo.data.remote.dto.TodoDelete
import com.task.todo.data.remote.dto.TodoDto
import com.task.todo.domain.repository.TodoRepository
import javax.inject.Inject

class TodoRepoImpl @Inject constructor(
    private val api: ApiService,
) : TodoRepository {
    override suspend fun getTodoList(limit: Int, skip: Int): TodoDto {
        return api.getTodos(limit, skip)
    }

    override suspend fun postTodo(body: Todo): Todo {
        return api.postTodo(body)
    }

    override suspend fun updateTodo(body: Todo): Todo {
        return api.updateTodo(body.id)

    }

    override suspend fun deleteTodo(id: Int): TodoDelete {
        return api.deleteTodo(id)
    }


}

