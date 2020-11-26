package com.ydh.todoapp.data.remote

import com.google.gson.annotations.SerializedName
import com.ydh.todoapp.model.TodoModel

data class InsertResponse(
    @SerializedName("data")
    val data: TodoModel
)