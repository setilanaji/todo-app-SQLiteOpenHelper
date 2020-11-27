package com.ydh.todoapp.data.remote

import android.content.Context
import android.os.StrictMode
import com.ydh.todoapp.data.TodoRepository
import com.ydh.todoapp.model.TodoBodyInsert
import com.ydh.todoapp.model.TodoBodyUpdate
import com.ydh.todoapp.model.TodoModel


class TodoRemoteRepository(context: Context): TodoRepository{


    override fun getAllTodo(): List<TodoModel> {
        TODO("Not yet implemented")

    }

    override fun insertTodo(todoModel: TodoModel): TodoModel {
        TODO("Not yet implemented")

    }


    override fun deleteTodo(id: Long): Long {
        TODO("Not yet implemented")

    }

    override fun updateTodo(todoModel: TodoModel): TodoModel {
        TODO("Not yet implemented")
    }

    override fun getAllTodoOnline(list: List<TodoModel>): List<TodoModel> {
        val policy = StrictMode.ThreadPolicy.Builder()
            .permitAll().build()
        StrictMode.setThreadPolicy(policy)
       val listTodo =  TodoClient.todoApiService.getAllTodo().execute().body()?.data as MutableList<TodoModel>
        val todoEdited = mutableListOf<TodoModel>()
        for (item in listTodo){
            for (x in list){
                if (item.id == x.id ){
                    item.favoriteStatus = true
                    println("found")
                }
            }
            todoEdited.add(item)
        }

        return todoEdited.toList()
    }

    override fun createTodoOnline(task: String, date: String): TodoModel {
        val policy= StrictMode.ThreadPolicy.Builder()
            .permitAll().build()
        StrictMode.setThreadPolicy(policy)
        return TodoClient.todoApiService.insertTodo(TodoBodyInsert(task)).execute().body()!!.data
    }

    override fun updateTodoById(todoModel: TodoModel): TodoModel {
        val policy= StrictMode.ThreadPolicy.Builder()
            .permitAll().build()
        StrictMode.setThreadPolicy(policy)
        return TodoClient.todoApiService.updateTodo(todoModel.id , TodoBodyUpdate(todoModel.task, todoModel.completeStatus)).execute().body()!!.data
    }

    override fun deleteTodoById(id: Long): Long {
        val policy= StrictMode.ThreadPolicy.Builder()
            .permitAll().build()
        StrictMode.setThreadPolicy(policy)
        println("id $id")
        return if (TodoClient.todoApiService.deleteTodo(id).execute().body()!!.status) id else -1
    }


}