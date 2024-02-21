package com.task.todo.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.task.todo.data.local.dao.TaskHistoryDao
import com.task.todo.data.local.entity.TaskEntity


@Database(
    version = 1,
    entities = [
        TaskEntity::class
    ]
)
abstract class TODOAppDatabase : RoomDatabase()  {
    abstract fun taskHistoryDao(): TaskHistoryDao
}