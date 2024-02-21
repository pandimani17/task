package com.task.todo.presentation.taskdetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.task.todo.common.Constants
import com.task.todo.domain.model.TodoParcel
import com.task.todo.presentation.taskdetail.events.TaskDetailEvents
import com.task.todo.presentation.widgets.DialogComponent
import com.task.todo.presentation.widgets.ErrorWidget


@Composable
fun TaskDetailScreen(
    viewModel: TaskDetailViewModel = hiltViewModel(),
    navController: NavController,
    onAddTaskClosed: () -> Unit

) {
    val todo: TodoParcel? = navController.previousBackStackEntry?.savedStateHandle?.get("todo")
    val context = LocalContext.current
    val functionCalled = remember { mutableStateOf(false) }
    if (!functionCalled.value) {
        viewModel.setTodo(todo)
        functionCalled.value = true
    }
    DisposableEffect(Unit) {
        onDispose {
            onAddTaskClosed()
        }
    }
    if(viewModel.state.value.isDeleted)
        DialogComponent({ Text(text = Constants.deleted) }, {}, {}, {}, {
            navController.popBackStack()
        })


    Column( modifier = Modifier.fillMaxSize()
    ) {
        OutlinedTextField(
            value = viewModel.state.value.description,
            onValueChange = { newText ->
                viewModel.onEvent(TaskDetailEvents.DescriptionChanged(newText))
            },
            label = { Text("Enter Description") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp, start = 25.dp, end = 25.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            textStyle = MaterialTheme.typography.body1,
            singleLine = true,
            maxLines = 1,
            isError = false
        )
        if(!viewModel.state.value.desError.isNullOrEmpty())
            ErrorWidget(Constants.descriptionError,MaterialTheme.typography.body1.copy(color = Color.Red), modifier = Modifier.padding(top = 16.dp, start = 21.dp))
        if(!viewModel.state.value.successMsg.isNullOrEmpty())
            ErrorWidget(viewModel.state.value.successMsg.toString(),MaterialTheme.typography.body1.copy(color = Color.Green), modifier = Modifier.padding(top = 16.dp, start = 21.dp))

        Row(
            modifier = Modifier.padding(top = 16.dp, bottom = 10.dp, start = 10.dp)
        ) {
            Checkbox(
                checked = viewModel.state.value.completed,
                onCheckedChange = { isChecked ->
                    viewModel.onEvent(TaskDetailEvents.CheckCompleted(true))
                },
            )

            Text(
                text = "Completed",
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(start = 3.dp,top = 10.dp)
            )
        }
        Row(
            modifier = Modifier.padding( bottom = 10.dp, start = 10.dp)
        ) {
            Checkbox(
                checked = viewModel.state.value.notCompleted,
                onCheckedChange = { isChecked ->
                    viewModel.onEvent(TaskDetailEvents.CheckCompleted(false))
                },
            )

            Text(
                text = "Not Completed",
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(start = 3.dp, top = 10.dp)
            )
        }

        Row {
            Button(
                onClick = {
                    viewModel.onEvent(TaskDetailEvents.Submit(context))
                },
                modifier = Modifier
                    .padding(top = 50.dp, start = 40.dp, end = 20.dp),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors()
            ) {
                Text(text = "Update",modifier = Modifier.padding(top = 5.dp, bottom = 5.dp, start = 10.dp, end = 10.dp))
            }
            Button(
                onClick = {
                    viewModel.onEvent(TaskDetailEvents.Delete(context))

                },
                modifier = Modifier
                    .padding(top = 50.dp, start = 20.dp, end = 30.dp),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors()
            ) {
                Text(text = "Delete", modifier = Modifier.padding(top = 5.dp, bottom = 5.dp, start = 10.dp, end = 10.dp))
            }
        }
    }
}