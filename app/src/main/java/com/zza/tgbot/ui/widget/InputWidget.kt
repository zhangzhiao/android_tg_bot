package com.zza.tgbot.ui.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.zza.tgbot.ui.activity.TodoItem


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
    }
}