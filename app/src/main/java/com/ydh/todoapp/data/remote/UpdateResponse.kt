package com.ydh.todoapp.data.remote

import com.google.gson.annotations.SerializedName
import com.ydh.todoapp.model.TodoModel

data class UpdateResponse (
    @SerializedName("data")
    val data: TodoModel
)