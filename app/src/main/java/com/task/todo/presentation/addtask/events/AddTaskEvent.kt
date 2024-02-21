package com.task.todo.presentation.addtask.events

import android.content.Context

sealed class AddTaskEvent {

    data class TodoChanged(val todoDescription: String) : AddTaskEvent()

    data class  TodoSubmitted(val context: Context) : AddTaskEvent()

}
