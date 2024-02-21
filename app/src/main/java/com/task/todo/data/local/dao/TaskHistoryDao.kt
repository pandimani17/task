package com.task.todo.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.task.todo.data.local.entity.TaskEntity

@Dao
interface TaskHistoryDao {

    @Insert(onConflict = REPLACE)
    suspend fun addTasks(task: List<TaskEntity>)

    @Insert(onConflict = REPLACE)
    suspend fun addTask(task: TaskEntity)

    @Delete
    suspend fun deleteTask(task: TaskEntity)

    @Update
    suspend fun updateTask(task: TaskEntity)

    @Query("SELECT * from task_history")
    suspend fun getAllTasks(): List<TaskEntity>
}