package com.ydh.todoapp.data

import com.ydh.todoapp.data.remote.InsertResponse
import com.ydh.todoapp.data.remote.TodoResponse
import com.ydh.todoapp.model.TodoModel
import retrofit2.Call

interface TodoRepository {
    fun getAllTodo(): List<TodoModel>
    fun insertTodo(task: String, date: String): TodoModel
    fun deleteTodo(id: Long): Long
    fun updateTodo(todoModel: TodoModel): TodoModel


    fun getAllTodoOnline(): List<TodoModel>
    fun createTodoOnline(task: String, date: String): TodoModel
    fun updateTodoById(todoModel: TodoModel): TodoModel
    fun deleteTodoById(id: Long): Long

}