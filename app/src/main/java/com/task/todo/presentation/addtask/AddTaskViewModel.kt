package com.task.todo.presentation.addtask

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.todo.common.NetworkUtils
import com.task.todo.common.Resource
import com.task.todo.common.Utilities
import com.task.todo.data.remote.dto.Todo
import com.task.todo.domain.use_case.AddTodoLocalUseCase
import com.task.todo.domain.use_case.PostTodoUseCase
import com.task.todo.domain.use_case.ValidateDescriptionUSeCase
import com.task.todo.presentation.addtask.events.AddTaskEvent
import com.task.todo.presentation.addtask.states.AddTaskState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class AddTaskViewModel @Inject constructor(
    private val postTodoUseCase: PostTodoUseCase,
    private val addTodoLocalUseCase: AddTodoLocalUseCase,
    private val validateDescription: ValidateDescriptionUSeCase,
    private val networkUtils: NetworkUtils,

    ) : ViewModel() {

    var state = mutableStateOf(AddTaskState())
    var internetNotAvailable = mutableStateOf(false)
    private val todoReq = lazy { Todo(completed = false, todo = "", userId = 0, id = 0) }


    fun onEvent(event: AddTaskEvent) {
        when (event) {
            is AddTaskEvent.TodoChanged -> {
                state.value = state.value.copy(todo = event.todoDescription)
            }

            is AddTaskEvent.TodoSubmitted -> {
                if (networkUtils.isInternetAvailable(event.context)) {
                    submitDescription(state.value.todo)
                    internetNotAvailable.value = false
                } else
                    internetNotAvailable.value = true
            }
        }

    }

    private fun submitDescription(des: String) {
        val descResult = validateDescription.invoke(des)
        if (!descResult.successful) {
            state.value = state.value.copy(desError = descResult.errorMessage, successMsg = null)
        }else{
            state.value = state.value.copy(desError = null, successMsg = null)
            addTodo()
        }

    }

    private fun addTodo() {
        postTodoUseCase.invoke(
            todoReq.value.copy(
                completed = false,
                todo = state.value.todo,
                userId = (0..100).random(),
                id = 0
            )
        ).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    state.value = state.value.copy(isLoading = false, todo = "", successMsg = "Todo Added")
                    result.data?.let { addTodoLocalUseCase.invoke(it).launchIn(viewModelScope) }
                }

                is Resource.Error -> {
                    state.value = state.value.copy(isLoading = false)

                }

                is Resource.Loading -> {
                    state.value = state.value.copy(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}