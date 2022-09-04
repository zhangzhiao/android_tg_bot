package com.zza.tgbot.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.zza.tgbot.ui.activity.page.ListPage
import com.zza.tgbot.ui.theme.TgBotTheme
import com.zza.tgbot.ui.widget.*


/**
 * @Author： created by zhangZhiAo
 * @CreateTime: 2022/8/30 15:08
 * @Describe:
 */

class PageMainActivity : ComponentActivity() {
    val items = listOf("Home", "Love", "Other")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TgBotTheme {
                val selectedIndex = remember { mutableStateOf(0) }
                Column(
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        when (selectedIndex.value) {
                            0 -> ListPage()
                            1 -> ImageWatcher()
                            2 -> LrcPlayerTest(modifier = Modifier)
                        }
                    }
                    BottomNavigationBar(
                        selectedIndex = selectedIndex,
                        onItemSelected = { selectedIndex.value = it },
                        modifier = Modifier.fillMaxWidth()
                    )
                }

            }
        }
    }


    @Composable
    fun BottomNavigationBar(
        selectedIndex: MutableState<Int>,
        onItemSelected: (Int) -> Unit,
        modifier: Modifier = Modifier
    ) {
        NavigationBar(modifier = modifier) {
            items.forEachIndexed { index, s ->
                val iconColor: FloatArray = remember() {
                    floatArrayOf(
                        randomAlpha(),
                        randomColor(),
                        randomColor(),
                        randomColor()
                    )
                }
                NavigationBarItem(
                    selected = selectedIndex.value == index,
                    onClick = { onItemSelected(index) },
                    icon = {
                        Icon(
                            Icons.Filled.Favorite,
                            s,
                            tint = LocalContentColor.current.copy(
                                iconColor[0],
                                iconColor[1],
                                iconColor[2],
                                iconColor[3]
                            )
                        )
                    },
                    label = { Text(text = s) }
                )
            }
        }
    }
}