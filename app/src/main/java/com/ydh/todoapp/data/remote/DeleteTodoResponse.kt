package com.ydh.todoapp.data.remote

import com.google.gson.annotations.SerializedName
import com.ydh.todoapp.model.TodoModel

data class DeleteTodoResponse(
    @SerializedName("status")
    val status: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: String
)
