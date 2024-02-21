package com.task.todo.domain.use_case

import com.task.todo.common.Resource
import com.task.todo.domain.repository.TodoRepositoryLocal
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTodoListLocalUseCase @Inject constructor(
    private val repository: TodoRepositoryLocal
) {
     operator fun invoke() = flow{
        try {
            val data = repository.getTodoListFromLocal()
            emit(Resource.Success(data))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message))
        }
    }


}