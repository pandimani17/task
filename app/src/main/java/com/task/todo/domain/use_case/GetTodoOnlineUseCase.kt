package com.task.todo.domain.use_case

import com.task.todo.common.Resource
import com.task.todo.data.remote.dto.toTodos
import com.task.todo.domain.repository.TodoRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class GetTodoOnlineUseCase @Inject constructor(
    private val repository: TodoRepository
) {
     operator fun invoke(limit: Int, skip: Int) = flow {
        try {
            emit(Resource.Loading())
            val data = repository.getTodoList(limit,skip).toTodos()
            emit(Resource.Success(data))
        }catch (e : HttpException){
            emit(Resource.Error(message = "Network Error"))
        }
         catch (e : Exception){
             emit(Resource.Error(message = "Network Error"))

         }
         catch (e: java.lang.Exception){
             emit(Resource.Error(message = "Network Error"))

         }


    }
}