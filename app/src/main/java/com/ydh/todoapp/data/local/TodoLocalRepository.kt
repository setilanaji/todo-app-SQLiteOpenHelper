package com.ydh.todoapp.data.local

import android.content.ContentValues
import android.content.Context
import androidx.core.database.getIntOrNull
import androidx.core.database.getLongOrNull
import androidx.core.database.getStringOrNull
import com.ydh.todoapp.data.TodoRepository
import com.ydh.todoapp.model.TodoModel

class TodoLocalRepository(context: Context) : TodoRepository {
    private val localDatabase by lazy { LocalDB(context) }

    override fun getAllTodo(): List<TodoModel> {
        val db = localDatabase.readableDatabase
        val order = "${TodoEntity.COLUMN_ID} DESC"
        val cursor = db.query(
            TodoEntity.TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            order
        )
        val todo = mutableListOf<TodoModel>()

        while (cursor.moveToNext()) {
            val id = cursor.getLongOrNull(cursor.getColumnIndexOrThrow(TodoEntity.COLUMN_ID))
            val name = cursor.getStringOrNull(cursor.getColumnIndexOrThrow(TodoEntity.COLUMN_TASK))
            val status = cursor.getIntOrNull(cursor.getColumnIndexOrThrow(TodoEntity.COLUMN_COMPLETE_STATUS)) ?: 0
            val date = cursor.getStringOrNull(cursor.getColumnIndexOrThrow(TodoEntity.COLUMN_DATE))

            if (id != null && name != null && date != null ) {
                val todoModel = TodoModel(id, name, status == 1, date)

                todo.add(todoModel)
            }
        }

        cursor.close()

        return todo
    }

    override fun insertTodo(todoModel: TodoModel): TodoModel {
        val db = localDatabase.writableDatabase
        val values = ContentValues().apply {
            put(TodoEntity.COLUMN_TASK, todoModel.task)
            put(TodoEntity.COLUMN_ID, todoModel.id)
            put(TodoEntity.COLUMN_COMPLETE_STATUS, if (todoModel.completeStatus) 1 else 0)
            put(TodoEntity.COLUMN_DATE,todoModel.date )
        }

        val id = db.insert(TodoEntity.TABLE_NAME, TodoEntity.COLUMN_ID, values)
        db.close()

        return TodoModel(todoModel.id, todoModel.task, todoModel.completeStatus, date = todoModel.date)
    }

    override fun deleteTodo(id: Long): Long {
        val db = localDatabase.writableDatabase
        val selection = "${TodoEntity.COLUMN_ID} = ?"
        val selectionArgs = arrayOf("$id")

        db.delete(TodoEntity.TABLE_NAME, selection, selectionArgs)
        db.close()

        return id
    }

    override fun updateTodo(todoModel: TodoModel): TodoModel {
        val db = localDatabase.writableDatabase
        val values = ContentValues().apply {
            put(TodoEntity.COLUMN_TASK, todoModel.task)
            put(TodoEntity.COLUMN_COMPLETE_STATUS, if (todoModel.completeStatus) 1 else 0)
            put(TodoEntity.COLUMN_DATE,todoModel.date )

        }
        val selection = "${TodoEntity.COLUMN_ID} = ? "
        val selectionArgs = arrayOf("${todoModel.id}" )

        db.update(TodoEntity.TABLE_NAME, values, selection, selectionArgs)
        db.close()

        return todoModel
    }

    override fun getAllTodoOnline(list: List<TodoModel>): List<TodoModel> {
        TODO("Not yet implemented")
    }

    override fun createTodoOnline(task: String, date: String): TodoModel {
        TODO("Not yet implemented")
    }

    override fun updateTodoById(todoModel: TodoModel): TodoModel {
        TODO("Not yet implemented")
    }

    override fun deleteTodoById(id: Long): Long {
        TODO("Not yet implemented")
    }

}