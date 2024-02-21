package com.task.todo.presentation.widgets

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.DialogProperties

@Composable
fun DialogComponent(
    title: @Composable () -> Unit,
    onDismissRequest: () -> Unit,
    body: @Composable () -> Unit,
    dismissButton: @Composable () -> Unit,
    onConfirm: () -> Unit
) {
    var dialogOpen by remember {
        mutableStateOf(true)
    }
    if (dialogOpen) {
        AlertDialog(
            onDismissRequest = {
                dialogOpen = false
            },
            title = title,
            confirmButton = {
                Button(onClick = {
                    onConfirm()
                    dialogOpen = false
                }) {
                    Text("Ok")
                }
            },
            )
    }
}