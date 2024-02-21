package com.task.todo.domain.use_case

import com.task.todo.common.Resource
import com.task.todo.data.remote.dto.Todo
import com.task.todo.domain.repository.TodoRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class UpdateTodoOnlineUseCase @Inject constructor(
    private val repository: TodoRepository
) {

    operator fun invoke(body : Todo) = flow{
        try {
            emit(Resource.Loading())
            val data = repository.updateTodo(body)
            emit(Resource.Success(data))
        } catch (e: HttpException) {
            emit(Resource.Error(message = "Network Error"))
        }
    }
}