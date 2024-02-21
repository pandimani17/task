package com.task.todo.presentation.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun ListItem(msgText : String,status : Boolean, onclick: () -> Unit){
    Card(
        shape = RoundedCornerShape(5.dp),
        backgroundColor = Color.White,
        modifier = Modifier.fillMaxWidth().clickable {
            onclick()
        }
    ) {
        Text(
            modifier = Modifier.padding(start = 28.dp, bottom = 20.dp, top = 10.dp),
            text = if(status)"Completed" else "Not Completed",
            style = MaterialTheme.typography.body1
        )

        Column {
            Row(modifier = Modifier.drawWithCache {
                onDrawWithContent {
                    drawLine(
                        color = Color.Red,
                        start = Offset.Zero,
                        end = Offset(0f, this.size.height),
                        strokeWidth = 25f
                    )
                    drawContent()
                }
            }) {
                Row(
                    modifier = Modifier.padding(top = 30.dp, bottom = 20.dp, start = 20.dp),
                ) {
                    Text(
                        modifier = Modifier.padding(start = 8.dp),
                        text = msgText,
                        style = MaterialTheme.typography.body1
                    )
                }

            }
        }

    }
}