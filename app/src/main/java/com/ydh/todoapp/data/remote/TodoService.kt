package com.ydh.todoapp.data.remote

import com.ydh.todoapp.model.TodoBodyInsert
import retrofit2.Call
import retrofit2.http.*

interface TodoService {
    @GET("api/v1/todos")
    fun getAllTodo(): Call<TodoResponse>

    @POST("api/v1/todos")
     fun insertTodo(
        @Body
        body: TodoBodyInsert
    ): Call<InsertResponse>

    @DELETE("api/v1/todos/{id}")
    fun deleteTodo(@Path("id") id: Long): Call<DeleteTodoResponse>


}