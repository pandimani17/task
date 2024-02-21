package com.task.todo.domain.use_case

import com.task.todo.common.Resource
import com.task.todo.data.remote.dto.Todo
import com.task.todo.domain.repository.TodoRepositoryLocal
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class DeleteTodoLocalUseCase @Inject constructor(
    private val repositoryLocal: TodoRepositoryLocal

) {
    operator fun invoke(todo : Todo) = flow {
        try {
            emit(Resource.Loading())
            val data =repositoryLocal.deleteTodo(todo)
            emit(Resource.Success(data))
        } catch (e: HttpException) {
            emit(Resource.Error(message = "Network Error"))
        }
    }
}