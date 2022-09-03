package com.zza.tgbot.ui.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.zza.tgbot.R

@Composable
fun PhotographerCard(modifier: Modifier = Modifier) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp))
            .background(color = MaterialTheme.colorScheme.surface)
            .clickable(onClick = {})
            .onGloballyPositioned {
            }
    ) {

        Surface(
            modifier = Modifier
                .size(50.dp)
                .padding(4.dp),
            shape = CircleShape,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
        ) {
            Image(
                painter = painterResource(id = R.mipmap.msg),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
        Column(
            modifier = Modifier
                .padding(start = 8.dp)
                .align(Alignment.CenterVertically)
        ) {
            Text(text = "Zhang ZhiAO", fontWeight = FontWeight.Bold)
            CompositionLocalProvider(LocalContentColor provides LocalContentColor.current) {
                Text(text = "a few ago", style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}