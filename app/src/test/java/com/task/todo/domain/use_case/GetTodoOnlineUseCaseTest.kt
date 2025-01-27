package com.task.todo.domain.use_case

import com.task.todo.data.remote.dto.Todo
import com.task.todo.data.remote.dto.TodoDto
import com.task.todo.domain.repository.TodoRepository
import org.mockito.Mockito.mock
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.`when`
import retrofit2.HttpException
import retrofit2.Response


class GetTodoOnlineUseCaseTest {


    private val repository : TodoRepository  = mock()




    @Test
    fun test_api() = runTest{
         val listOfTodos = listOf(Todo(completed = true,id=1,todo = "sample", userId = 2),Todo(completed = true,id=2,todo = "sample1", userId = 3))
         val results = TodoDto(2,10,listOfTodos,10)
        `when`(repository.getTodoList(2,10)).thenReturn(results)
         val data = repository.getTodoList(2,10)
         assertEquals(listOfTodos, data.todos)
}

    @Test
    fun test_httpError() = runTest {
        val response = Response.error<List<Todo>>(404, "Not Found".toResponseBody(null))
        `when`(repository.getTodoList(2,10)).thenThrow(HttpException(response))
        try {
            repository.getTodoList(2, 10)
            assert(false)
        } catch (e: HttpException) {
            assert(e.code() == 404)
        }

    }


}