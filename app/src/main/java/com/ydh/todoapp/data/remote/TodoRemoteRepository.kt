package com.ydh.todoapp.data.remote

import android.content.Context
import android.os.StrictMode
import com.ydh.todoapp.data.TodoRepository
import com.ydh.todoapp.model.TodoBodyInsert
import com.ydh.todoapp.model.TodoModel


class TodoRemoteRepository(context: Context): TodoRepository{


    override fun getAllTodo(): List<TodoModel> {
        val policy = StrictMode.ThreadPolicy.Builder()
                .permitAll().build()
        StrictMode.setThreadPolicy(policy)
        return TodoClient.todoApiService.getAllTodo().execute().body()?.data as MutableList<TodoModel>
    }

    override fun insertTodo(task: String, date: String): TodoModel {
        val policy= StrictMode.ThreadPolicy.Builder()
                .permitAll().build()
        StrictMode.setThreadPolicy(policy)
        return TodoClient.todoApiService.insertTodo(TodoBodyInsert(task)).execute().body()!!.data
    }


    override fun deleteTodo(id: Long): Long {
        return TodoClient.todoApiService.deleteTodo(id).execute().body()!!.data.toLong()
    }

    override fun updateTodo(todoModel: TodoModel): TodoModel {
        TODO("Not yet implemented")
    }


}