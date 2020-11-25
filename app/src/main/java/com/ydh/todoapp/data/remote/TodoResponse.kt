package com.ydh.todoapp.data.remote

import com.google.gson.annotations.SerializedName
import com.ydh.todoapp.model.TodoModel

data class TodoResponse(
    @SerializedName("data")
    val data: List<TodoModel>
)