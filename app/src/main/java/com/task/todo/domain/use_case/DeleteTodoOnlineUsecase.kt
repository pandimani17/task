package com.task.todo.domain.use_case

import com.task.todo.common.Resource
import com.task.todo.domain.repository.TodoRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DeleteTodoOnlineUseCase @Inject constructor(
    private val repository: TodoRepository
) {
    operator fun invoke(id : Int) = flow{
        try {
            val data = repository.deleteTodo(id)
            emit(Resource.Success(data))
        } catch (e: Exception) {
            emit(Resource.Error(message = "Network Error"))
        }

    }

}