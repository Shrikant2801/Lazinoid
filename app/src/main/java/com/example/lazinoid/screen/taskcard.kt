package com.example.lazinoid.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lazinoid.data.Task

@Composable
fun TaskCard(
    task: Task,
    onToggleComplete: (Task) -> Unit,
    onDelete: (Task) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .shadow(10.dp, ambientColor = Color.Cyan, spotColor = Color.Magenta)
            .clickable { onToggleComplete(task) },
        colors = CardDefaults.cardColors(
            containerColor = if (task.completed) Color(0xFF1B5E20) else Color(0xFF212121)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = task.title,
                style = TextStyle(
                    fontSize = 18.sp,
                    color = Color.Cyan,
                    fontWeight = FontWeight.Bold,
                    textDecoration = if (task.completed) TextDecoration.LineThrough else TextDecoration.None
                )
            )
            IconButton(onClick = { onDelete(task) }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete",
                    tint = Color.Magenta
                )
            }
        }
    }
}
