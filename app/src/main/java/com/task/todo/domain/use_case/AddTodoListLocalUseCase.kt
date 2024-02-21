package com.task.todo.domain.use_case

import com.task.todo.common.Resource
import com.task.todo.data.remote.dto.Todo
import com.task.todo.domain.repository.TodoRepositoryLocal
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class AddTodoListLocalUseCase @Inject constructor(
    private val repository: TodoRepositoryLocal
) {

   operator fun invoke(todo: List<Todo>) = flow {
        try {
            val data = repository.addTodoListLocal(todo = todo)
            emit(Resource.Success(data))
        } catch (e: Exception) {
            emit(Resource.Error(message = "Network Error"))
        }


    }

}