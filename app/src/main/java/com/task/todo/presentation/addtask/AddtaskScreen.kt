package com.task.todo.presentation.addtask

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.task.todo.common.Constants
import com.task.todo.presentation.addtask.events.AddTaskEvent
import com.task.todo.presentation.widgets.DialogComponent
import com.task.todo.presentation.widgets.ErrorWidget
import com.task.todo.presentation.widgets.Loader


@Composable
fun AddTaskScreen(
    viewModel: AddTaskViewModel = hiltViewModel(),
    onAddTaskClosed: () -> Unit
) {
    val context = LocalContext.current
    if(viewModel.internetNotAvailable.value)
        DialogComponent({ Text(text = Constants.alertText) }, {}, {}, {}, {})

    DisposableEffect(Unit) {
        onDispose {
            onAddTaskClosed()
        }
    }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            "Add ToDo",
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(top = 25.dp,start = 25.dp)
        )
        OutlinedTextField(
            value = viewModel.state.value.todo,
            onValueChange = { newText ->
                viewModel.onEvent(AddTaskEvent.TodoChanged(newText))
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

        Button(
            onClick = {
                viewModel.onEvent(AddTaskEvent.TodoSubmitted(context))
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp, start = 30.dp, end = 30.dp),
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors()
        ) {
            Text(text = "Submit", modifier = Modifier.padding(top = 5.dp, bottom = 5.dp))
        }
        if(viewModel.state.value.isLoading)
            Loader()

    }

}