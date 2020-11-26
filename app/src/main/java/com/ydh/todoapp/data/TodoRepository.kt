package com.ydh.todoapp.data

import com.ydh.todoapp.model.TodoModel

interface TodoRepository {
    fun getAllTodo(): List<TodoModel>
    fun insertTodo(task: String, date: String): TodoModel
    fun deleteTodo(id: Long): Long
    fun updateTodo(todoModel: TodoModel): TodoModel


}