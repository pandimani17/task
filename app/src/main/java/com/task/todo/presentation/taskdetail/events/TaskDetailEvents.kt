package com.task.todo.presentation.taskdetail.events

import android.content.Context

sealed class TaskDetailEvents {

    data class DescriptionChanged(var description: String) : TaskDetailEvents()

    data class CheckCompleted(var checked : Boolean) : TaskDetailEvents()

    data class CheckNotCompleted(var checked : Boolean) : TaskDetailEvents()


    data class Submit(val context: Context) : TaskDetailEvents()

    data class Delete(val context: Context) : TaskDetailEvents()
}
