package com.task.todo.data.remote

import com.task.todo.data.remote.dto.Todo
import com.task.todo.data.remote.dto.TodoDelete
import com.task.todo.data.remote.dto.TodoDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("/todos")
    suspend fun getTodos(
        @Query("limit") policyNum: Int?,
        @Query("skip") niNo: Int?,
    ): TodoDto

    @POST("/todos/add")
    suspend fun postTodo(@Body body: Todo): Todo


    @PUT("/todos/{id}")
    suspend fun updateTodo(
        @Path("id") id: Int?,
    ): Todo


    @DELETE("/todos/{id}")
    suspend fun deleteTodo(
        @Path("id") id: Int?,

        ): TodoDelete


}