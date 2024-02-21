package com.task.todo.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_history")
data class TaskEntity(
    @ColumnInfo(name = "id")
    @PrimaryKey
    val id: Int = 0,
    @ColumnInfo(name = "todo") val todo: String,
    @ColumnInfo(name = "completed") val completed: Boolean,
    @ColumnInfo(name = "userId") val userID: Int
)