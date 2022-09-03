package com.zza.tgbot.ui.widget

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.dp
import com.zza.tgbot.ui.activity.TodoItem
import com.zza.tgbot.viewmodel.iconItems


/**
 * @Author： created by zhangZhiAo
 * @CreateTime: 2022/8/29 17:09
 * @Describe:
 */
@Composable
fun InputText(text: String, onTextChanged: (String) -> Unit, modifier: Modifier = Modifier) {
    TextField(
        value = text,
        onValueChange = onTextChanged,
        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
        modifier = modifier
    )
}

@Composable
fun InputButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    TextButton(
        onClick = onClick,
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(),
        modifier = modifier,
        enabled = enabled
    ) {
        Text(text = text)
    }
}

@Composable
fun InputWidget(onInputComplete: (TodoItem) -> Unit) {
    val (text, setText) = remember { mutableStateOf("") }
    val (icon, setIcon) = remember { mutableStateOf(Icons.Filled.Delete) }
    val isIconVisible = text.isNotEmpty()
    Column {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp)
        )
        {
            InputText(
                text = text,
                onTextChanged = setText,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            )
            InputButton(
                text = "Add",
                onClick = {
                    onInputComplete(TodoItem(text))
                    setText("")
                },
                modifier = Modifier.align(Alignment.CenterVertically),
                enabled = text.isNotEmpty()
            )
        }
        AnimatedIconRow(
            icon = icon,
            onIconChanged = setIcon,
            Modifier.padding(top = 8.dp),
            visible = isIconVisible
        )
    }

}

@Composable
fun AnimatedIconRow(
    icon: ImageVector,
    onIconChanged: (ImageVector) -> Unit,
    modifier: Modifier = Modifier,
    visible: Boolean = true
) {
    val enter = remember {
        fadeIn(animationSpec = TweenSpec(300, easing = FastOutSlowInEasing))
    }
    val exit = remember {
        fadeOut(animationSpec = TweenSpec(100, easing = FastOutSlowInEasing))
    }
    Box(modifier = Modifier.defaultMinSize(minHeight = 16.dp)) {
        AnimatedVisibility(
            visible = visible,
            enter = enter,
            exit = exit
        ) {
            IconRow(icon = icon, modifier = modifier, onIconChanged = onIconChanged)
        }
    }
}

@Composable
fun IconRow(
    icon: ImageVector,
    modifier: Modifier = Modifier,
    onIconChanged: (ImageVector) -> Unit
) {
    Row(modifier = modifier) {
        for (itemIcon in iconItems) {
            SelectableIconButton(
                icon = itemIcon,
                onIconSelected = { onIconChanged(itemIcon) },
                isSelected = icon == itemIcon,
                modifier = modifier
            )

        }
    }
}

@Composable
fun SelectableIconButton(
    icon: ImageVector,
    onIconSelected: () -> Unit,
    isSelected: Boolean,
    modifier: Modifier = Modifier
) {
    val tint = if (isSelected) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
    }
    androidx.compose.material3.TextButton(
        onClick = { onIconSelected() },
        shape = CircleShape,
        modifier = modifier
    ) {
        Column {
            androidx.compose.material3.Icon(
                imageVector = icon,
                tint = tint,
                contentDescription = null
            )
            if (isSelected) {
                Box(
                    modifier = Modifier
                        .padding(top = 3.dp)
                        .width(icon.defaultWidth)
                        .height(1.dp)
                        .background(tint)
                )
            } else {
                Spacer(modifier = Modifier.height(4.dp))
            }
        }

    }

}
