package com.zza.tgbot.ui.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun LayoutStudy(body: @Composable (modifier: Modifier) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "IM Test")
            }, actions = {
                IconButton(onClick = {}, modifier = Modifier) {
                    Icon(imageVector = Icons.Filled.Favorite, contentDescription = null)
                }
            })
        }
    ) {
        body(Modifier.padding(it))
    }
}