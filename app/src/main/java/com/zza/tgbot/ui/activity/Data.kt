package com.zza.tgbot.ui.activity

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.ui.graphics.vector.ImageVector
import java.util.*


/**
 * @Author： created by zhangZhiAo
 * @CreateTime: 2022/8/29 13:56
 * @Describe:
 */

data class TodoItem(
    val task: String,
    val icon: ImageVector = Icons.Filled.Favorite,
    val id: UUID = UUID.randomUUID()
)