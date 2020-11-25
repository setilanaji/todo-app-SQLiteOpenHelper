package com.ydh.todoapp.data.remote

import android.content.Context
import android.util.Log
import com.ydh.todoapp.data.TodoRepository
import com.ydh.todoapp.model.TodoModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TodoRemoteRepository(context: Context): TodoRepository{
    override fun getAllTodo(): List<TodoModel> {
        var todo = mutableListOf<TodoModel>()

            TodoClient.todoApiService.getAllTodo().enqueue(object : Callback<TodoResponse> {
                override fun onResponse(
                    call: Call<TodoResponse>,
                    response: Response<TodoResponse>
                ) {
                    if (response.isSuccessful){
                        todo = response.body()?.data as MutableList<TodoModel>
                    }

                }

                override fun onFailure(call: Call<TodoResponse>, t: Throwable) {
                    Log.e("getAllTodo", t.toString()
                    )
                }

            })

        return todo
    }

    override fun insertTodo(task: String, date: String): TodoModel {
        TODO("Not yet implemented")
    }

    override fun deleteTodo(id: Long): Long {
        TODO("Not yet implemented")
    }

    override fun updateTodo(todoModel: TodoModel): TodoModel {
        TODO("Not yet implemented")
    }

}