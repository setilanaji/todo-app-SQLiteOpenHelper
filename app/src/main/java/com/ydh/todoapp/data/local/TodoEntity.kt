package com.ydh.todoapp.data.local

class TodoEntity(val id: Long, val task: String) {
    companion object {
        const val TABLE_NAME = "todo_table"
        const val COLUMN_ID = "id"
        const val COLUMN_TASK = "task"
        const val COLUMN_COMPLETE_STATUS = "status"
        const val COLUMN_DATE = "date"
        const val SQL_CREATE_TODO =
            "CREATE TABLE $TABLE_NAME (" +
                    "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "$COLUMN_TASK TEXT" +
                    "$COLUMN_COMPLETE_STATUS INTEGER" +
                    "$COLUMN_DATE STRING)"
    }
}