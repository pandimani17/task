package com.task.todo.presentation.taskdetail

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.todo.common.Resource
import com.task.todo.common.Utilities
import com.task.todo.data.remote.dto.Todo
import com.task.todo.domain.model.TodoParcel
import com.task.todo.domain.use_case.DeleteTodoLocalUseCase
import com.task.todo.domain.use_case.DeleteTodoOnlineUseCase
import com.task.todo.domain.use_case.UpdateTodoLocalUseCase
import com.task.todo.domain.use_case.UpdateTodoOnlineUseCase
import com.task.todo.domain.use_case.ValidateDescriptionUSeCase
import com.task.todo.presentation.taskdetail.events.TaskDetailEvents
import com.task.todo.presentation.taskdetail.states.TaskDetailStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class TaskDetailViewModel @Inject constructor(
    private val updateTodoOnlineUseCase: UpdateTodoOnlineUseCase,
    private val deleteTodoOnlineUseCase: DeleteTodoOnlineUseCase,
    private val updateTodoLocalUseCase: UpdateTodoLocalUseCase,
    private val deleteTodoLocalUseCase: DeleteTodoLocalUseCase,
    private val validateDescription: ValidateDescriptionUSeCase

) : ViewModel() {

    var state = mutableStateOf(TaskDetailStates())
    private lateinit var todos: Todo


    fun onEvent(event: TaskDetailEvents) {
        when (event) {
            is TaskDetailEvents.DescriptionChanged -> {
                state.value = state.value.copy(description = event.description)

            }

            is TaskDetailEvents.Submit -> {
                submitTodo(state.value.description,event.context)

            }

            is TaskDetailEvents.CheckCompleted -> {
                state.value = state.value.copy(completed = event.checked, notCompleted = !event.checked)

            }

            is TaskDetailEvents.CheckNotCompleted -> {
                state.value = state.value.copy(completed = event.checked, notCompleted = !event.checked)

            }

            is TaskDetailEvents.Delete -> {
                if (Utilities.isInternetAvailable(event.context))
                    deleteTodoOnline()
                else
                    deleteTodoOffline()
            }


        }
    }

    private fun submitTodo(des : String,context: Context){
        val descResult = validateDescription.invoke(des)
        if (!descResult.successful) {
            state.value = state.value.copy(desError = descResult.errorMessage, successMsg = null)
        }else{
            state.value = state.value.copy(desError = null, successMsg = null)
            if (Utilities.isInternetAvailable(context))
                updateTodoOnline()
            else
                updateTodoOffline()
        }
    }

    fun setTodo(todo: TodoParcel?) {
        todos = Utilities.fromParcelable(todo)
        state.value.description = todos.todo
        if (todos.completed) state.value.completed = todos.completed else state.value.notCompleted =
            true
    }

    private fun updateTodoOnline() {
        updateTodoOnlineUseCase.invoke(
            todos.copy(
                todo = state.value.description,
                completed = if (state.value.completed) state.value.completed else false
            ),
        ).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    updateTodoOffline()
                }

                is Resource.Error -> {
                    state.value = state.value.copy(isLoading = true, successMsg = null)
                }

                is Resource.Loading -> {
                    state.value = state.value.copy(isLoading = true, successMsg = null)
                }
            }


        }.launchIn(viewModelScope)
    }

    private fun updateTodoOffline() {
        updateTodoLocalUseCase.invoke(
            todos.copy(
                completed = if (state.value.completed) state.value.completed else false,
                todo = state.value.description,
            )
        ).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    state.value = state.value.copy(isLoading = false, successMsg = "Todo Updated")
                }

                is Resource.Error -> {
                    state.value = state.value.copy(isLoading = false, successMsg = null)

                }

                is Resource.Loading -> {
                    state.value = state.value.copy(isLoading = true, successMsg = null)

                }
            }

        }.launchIn(viewModelScope)

    }

    private fun deleteTodoOnline() {
        deleteTodoOnlineUseCase.invoke(todos.id).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    deleteTodoOffline()
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

    private fun deleteTodoOffline() {
        deleteTodoLocalUseCase.invoke(todos).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    state.value.isDeleted = true
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