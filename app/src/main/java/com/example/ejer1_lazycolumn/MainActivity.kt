package com.example.ejer1_lazycolumn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.copy
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ejer1_lazycolumn.ui.theme.Ejer1_LazyColumnTheme
import com.example.ejer1_lazycolumn.ui.theme.Task
import kotlin.collections.addAll
import kotlin.collections.indexOf

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ejer1_LazyColumnTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    TaskListScreen()
}
@Composable
fun TaskListScreen() {
    val tasks = remember { mutableStateListOf<Task>() }

    LaunchedEffect(Unit) {
        tasks.addAll(
            listOf(
                Task(1, "Estudiar"),
                Task(2, "Hacer la comida"),
                Task(3, "Hacer la compra")
            )
        )
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(tasks) { task ->
            TaskItem(task) {
                val index = tasks.indexOf(task)
                tasks[index] = tasks[index].copy(isCompleted = !tasks[index].isCompleted)
            }
        }
    }
}

@Composable
fun TaskItem(task: Task, onTaskCompletedChange: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onTaskCompletedChange() }
    ) {
        Icon(
            imageVector = if (task.isCompleted) Icons.Filled.CheckCircle else Icons.Filled.RadioButtonUnchecked,
            contentDescription = if (task.isCompleted) "Task completed" else "Task pending",
            tint = if (task.isCompleted) Color.Green else Color.Gray
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = task.title)
        Spacer(Modifier.weight(1f))
        IconButton(onClick = onTaskCompletedChange) {
            Icon(
                imageVector = if (task.isCompleted) Icons.Filled.RadioButtonUnchecked else Icons.Filled.CheckCircle,
                contentDescription = if (task.isCompleted) "Mark as pending" else "Mark as completed",
                tint = if (task.isCompleted) Color.Gray else Color.Green
            )
        }
    }
}
