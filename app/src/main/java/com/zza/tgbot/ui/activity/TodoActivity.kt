package com.zza.tgbot.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.zza.tgbot.ui.widget.InputText
import com.zza.tgbot.ui.widget.InputWidget
import com.zza.tgbot.viewmodel.TodoViewModel
import com.zza.tgbot.viewmodel.iconItems
import kotlin.random.Random


/**
 * @Author： created by zhangZhiAo
 * @CreateTime: 2022/8/29 13:55
 * @Describe:
 */


class TodoActivity : ComponentActivity() {
    private val todoViewModel by viewModels<TodoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val items: List<TodoItem> by todoViewModel.todoItems.observeAsState(listOf())
            TodoScreen(
                items = items,
                onAdd = { todoViewModel.addItem(it) },
                onRemove = { todoViewModel.remove(it) }
            )
        }
    }

    @Composable
    fun TodoScreen(
        items: List<TodoItem>,
        onAdd: (todoItem: TodoItem) -> Unit,
        onRemove: (todoItem: TodoItem) -> Unit
    ) {
        Column(Modifier.fillMaxWidth()) {
            LazyColumn(
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(top = 8.dp)
            ) {
                items(items) {
                    TodoRow(item = it, modifier = Modifier.fillParentMaxWidth(), onRemove)
                }
            }
            Button(
                onClick = { onAdd(TodoItem("New", Icons.Filled.Notifications)) },
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "Add Random Item")
            }
            InputWidget(onInputComplete = {
                onAdd(it)
            })
        }
    }

    @Composable
    fun TodoRow(
        item: TodoItem,
        modifier: Modifier = Modifier,
        onItemClicked: (todoItem: TodoItem) -> Unit
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .clickable {
                    onItemClicked(item)
                },
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = item.task)
            val iconColor: FloatArray = remember(item.id) {
                floatArrayOf(
                    randomAlpha(),
                    randomColor(),
                    randomColor(),
                    randomColor()
                )
            }
            Icon(
                imageVector = item.icon,
                contentDescription = null,
                tint = LocalContentColor.current.copy(
                    iconColor[0],
                    iconColor[1],
                    iconColor[2],
                    iconColor[3]
                )
            )
        }
    }

    private fun randomAlpha(): Float {
        return Random.nextFloat().coerceIn(0.3f, 0.9f)
    }

    private fun randomColor(): Float {
        return Random.nextFloat().coerceIn(0f, 255f)
    }
}

