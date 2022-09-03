package com.zza.tgbot.viewmodel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zza.tgbot.ui.activity.TodoItem


/**
 * @Author： created by zhangZhiAo
 * @CreateTime: 2022/8/29 15:20
 * @Describe:
 */

val iconItems = mutableListOf<ImageVector>().apply {
    add(Icons.Filled.Lock)
    add(Icons.Filled.Close)
    add(Icons.Filled.Check)
    add(Icons.Filled.Create)
    add(Icons.Filled.Clear)
    add(Icons.Filled.AccountBox)
}

class TodoViewModel : ViewModel() {
    private val baseItems = mutableListOf<TodoItem>().apply {
        add(TodoItem("Lock", Icons.Filled.Lock))
        add(TodoItem("Close", Icons.Filled.Close))
        add(TodoItem("Call", Icons.Filled.Call))
    }
    private var _todoItems = MutableLiveData(baseItems.toList())

    val todoItems: LiveData<List<TodoItem>> = _todoItems

    fun addItem(item: TodoItem) {
        _todoItems.value = _todoItems.value!! + listOf(item)
    }

    fun remove(item: TodoItem) {
        _todoItems.value = _todoItems.value!!.toMutableList().also {
            it.remove(item)
        }
    }


}