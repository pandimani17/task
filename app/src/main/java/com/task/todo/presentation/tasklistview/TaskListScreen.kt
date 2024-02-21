package com.task.todo.presentation.tasklistview

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.task.todo.common.Constants
import com.task.todo.common.Utilities
import com.task.todo.presentation.navigation.Screens
import com.task.todo.presentation.widgets.DialogComponent
import com.task.todo.presentation.widgets.ListItem
import com.task.todo.presentation.widgets.Loader


@Composable
fun TaskListScreen(
    viewModel: TaskListViewModel = hiltViewModel(),
    navController: NavController,
    refreshData: MutableState<Boolean>

) {

    val lazyListState = rememberLazyListState()
    var items = viewModel.state.value.todo
    LaunchedEffect(refreshData.value) {
        if (refreshData.value) {
            viewModel.getTodoFromLocal()
            refreshData.value = false
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        LazyColumn(content = {
            itemsIndexed(items = items) { index, item ->
                if (index == items.size - 1 && !viewModel.state.value.isLoading) {
                    viewModel.getTodoListOnline(10, skip = items.size)
                }
                ListItem(items[index].todo, items[index].completed) {
                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        "todo",
                        Utilities.toParcelable(items[index])
                    ) // new
                    navController.navigate(Screens.TaskDetailScreen.route)
                }
                Divider(modifier = Modifier.padding(top = 30.dp, bottom = 30.dp))

            }
        }, state = lazyListState)

        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            FloatingActionButton(onClick = {
                navController.navigate(Screens.AddTaskScreen.route)
            }) {
                Icon(Icons.Filled.Add, "")
            }
        }


    }
    if(viewModel.state.value.isLoading)
        Loader()


}