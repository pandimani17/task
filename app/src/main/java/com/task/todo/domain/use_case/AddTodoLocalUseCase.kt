package com.task.todo.domain.use_case

import com.task.todo.common.Resource
import com.task.todo.data.remote.dto.Todo
import com.task.todo.domain.repository.TodoRepositoryLocal
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class AddTodoLocalUseCase @Inject constructor(
    private val repositoryLocal: TodoRepositoryLocal
) {

    operator fun invoke(todo : Todo) = flow{
        try {
            val data = repositoryLocal.addTodo(todo)
            emit(Resource.Success(data))
        } catch (e: Exception) {
            emit(Resource.Error(message = "Network Error"))
        }
    }
}