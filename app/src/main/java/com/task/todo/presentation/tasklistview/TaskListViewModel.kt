package com.task.todo.presentation.tasklistview


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.todo.common.Resource
import com.task.todo.domain.model.Todos
import com.task.todo.domain.use_case.AddTodoListLocalUseCase
import com.task.todo.domain.use_case.GetTodoListLocalUseCase
import com.task.todo.domain.use_case.GetTodoOnlineUseCase
import com.task.todo.presentation.tasklistview.states.TodoListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class TaskListViewModel @Inject constructor(
    private val todoListUseCase: GetTodoOnlineUseCase,
    private val addTodoListLocalUseCase: AddTodoListLocalUseCase,
    private val getTodoListLocalUseCase: GetTodoListLocalUseCase
) : ViewModel() {

    var state = mutableStateOf(TodoListState(todoList = Todos(), todo = emptyList()))

    init {
        getTodoFromLocal()
    }


    fun getTodoListOnline(limit: Int, skip: Int) {
        todoListUseCase.invoke(limit, skip = skip).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    state.value = state.value.copy(
                        todoList = result.data ?: Todos(),
                        isLoading = false,
                        error = "",
                        todo = state.value.todo + result.data?.todos!!
                    )
                    addTodoListLocalUseCase.invoke(result.data.todos).launchIn(viewModelScope)
                }

                is Resource.Error -> {
                    state.value = state.value.copy(isLoading = false, error = "Network Error")

                }

                is Resource.Loading -> {
                    state.value = state.value.copy(isLoading = true)
                }

            }

        }.launchIn(viewModelScope)
    }

    fun getTodoFromLocal() {
        getTodoListLocalUseCase.invoke().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    if (result.data?.size == 0) {
                            getTodoListOnline(10, 0)
                    }
                    else
                        state.value = result.data?.let {
                            state.value.copy(
                                todoList = Todos(),
                                isLoading = false,
                                error = "",
                                todo = it
                            )
                        }!!
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