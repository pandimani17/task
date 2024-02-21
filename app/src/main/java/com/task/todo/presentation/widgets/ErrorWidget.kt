package com.task.todo.presentation.widgets

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun ErrorWidget(errText : String,textStyle: TextStyle,modifier: Modifier) {
    Row(modifier = modifier, verticalAlignment = Alignment.Top) {
        Text(
            text = errText,
            style = textStyle,
            modifier = Modifier.padding(start = 5.dp)
        )
    }

}