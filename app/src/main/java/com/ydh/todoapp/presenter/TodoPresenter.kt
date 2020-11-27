package com.ydh.todoapp.presenter

import com.ydh.todoapp.TodoContract
import com.ydh.todoapp.data.TodoRepository
import com.ydh.todoapp.model.TodoModel

class TodoPresenter(private val view: TodoContract.View, private val repository: TodoRepository) :
    TodoContract.Presenter {


    override fun getAllTodo(todo: List<TodoModel>) {
            val todoList by lazy { repository.getAllTodoOnline(todo) }
//        println(todoList)
        view.onSuccessGetAllTodo(todoList)
    }

    override fun updateTodo(todoModel: TodoModel) {
        val todo by lazy { repository.updateTodoById(todoModel) }
        view.onSuccessUpdateTodo(todo)
    }

    override fun getAllFavTodo(): List<TodoModel> {
        val todo by lazy { repository.getAllTodo() }
        view.onSuccessGetAllFavTodo(todo)
        return todo
    }

    override fun insertFavTodo(todoModel: TodoModel) {
        val todo by lazy { repository.insertTodo(todoModel) }
        view.onSuccessInsertFavTodo(todo)
    }

    override fun deleteFavTodo(todoModel: TodoModel) {
        val todoId by lazy { repository.deleteTodo(todoModel.id) }
        view.onSuccessDeleteFavTodo(todoId)
    }

    override fun updateFavTodo(todoModel: TodoModel) {
        val todo by lazy { repository.updateTodo(todoModel) }
        view.onSuccessUpdateTodo(todo)
    }

    override fun insertTodo(task: String, date: String) {
        val todo by lazy { repository.createTodoOnline(task, date) }
        view.onSuccessInsertTodo(todo)
    }

    override fun deleteTodo(id: Long) {
        val todoId by lazy { repository.deleteTodoById(id) }
        println("check id $todoId")
        view.onSuccessDeleteTodo(todoId)
    }
}