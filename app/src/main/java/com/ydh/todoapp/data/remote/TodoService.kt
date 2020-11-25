package com.ydh.todoapp.data.remote

import com.ydh.todoapp.data.TodoRepository
import com.ydh.todoapp.model.TodoBodyInsert
import com.ydh.todoapp.model.TodoModel
import retrofit2.Call
import retrofit2.http.*

interface TodoService {
    @GET("api/v1/todos")
    fun getAllTodo(): Call<TodoResponse>

    @POST("api/v1/todos")
     fun insertTodo(
        @Body
        body: TodoBodyInsert
    ): Call<TodoResponse>

//    @DELETE("api/v1/todos")
//    fun deleteTodo(id: Long): Long
//
//    @PUT("api/v1/todos")
//    fun updateTodo(todoModel: TodoModel): Call<>
}