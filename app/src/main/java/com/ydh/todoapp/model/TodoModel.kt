package com.ydh.todoapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TodoModel(
    @SerializedName("id")
    val id: Long,
    @SerializedName("task")
    var task: String,
    @SerializedName("status")
    var completeStatus: Boolean = false,
    @SerializedName("date")
    var date: String
    ):
    Parcelable