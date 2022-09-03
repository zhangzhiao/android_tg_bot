package com.zza.tgbot.ui.widget

import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage


/**
 * @Author： created by zhangZhiAo
 * @CreateTime: 2022/9/2 11:45
 * @Describe:
 */

@Composable
fun ImageWatcher(modifier: Modifier = Modifier) {
    val scale = remember { mutableStateOf(1f) }
    val smallBoxVisible = remember {
        mutableStateOf(true)
    }
    val smallBoxOffset = remember { mutableStateOf(Offset(0f, 0f)) }
    val enter = remember {
        // Specifies the starting offset of the slide-in to be 1/4 of the width to the right,
        // 100 (pixels) below the content position, which results in a simultaneous slide up
        // and slide left.
        slideIn(tween(100, easing = LinearOutSlowInEasing)) { fullSize ->
            IntOffset(smallBoxOffset.value.x.toInt(), 100)
        }
    }
    val exit = remember {
        slideOut(tween(100, easing = FastOutSlowInEasing)) { fullSize ->
            IntOffset(smallBoxOffset.value.x.toInt(), 100)
        }
    }
    Box(
        modifier = modifier
            .size((130 * scale.value).dp)
            .clickable { smallBoxVisible.value = !smallBoxVisible.value }
            .background(MaterialTheme.colors.onSurface)
            .onGloballyPositioned {
                smallBoxOffset.value = it.positionInWindow()
            }
    ) {
    }

    AnimatedVisibility(
        visible = !smallBoxVisible.value,
        enter = enter,
        exit = exit
    ) {
        Box(
            modifier = modifier
                .height(LocalConfiguration.current.screenHeightDp.dp)
                .width(LocalConfiguration.current.screenWidthDp.dp)
                .clickable { smallBoxVisible.value = !smallBoxVisible.value }
                .background(MaterialTheme.colors.onSurface)
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                model = "https://img2.baidu.com/it/u=4193452741,1851099504&fm=253&fmt=auto&app=138&f=JPEG?w=450&h=253",
                contentDescription = null
            )

        }
    }


}