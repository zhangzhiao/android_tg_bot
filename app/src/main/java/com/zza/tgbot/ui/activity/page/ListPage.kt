package com.zza.tgbot.ui.activity.page

import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.drake.brv.utils.linear
import com.drake.brv.utils.setup
import com.zza.tgbot.R
import com.zza.tgbot.ui.widget.LayoutStudy
import com.zza.tgbot.ui.widget.PhotographerCard
import com.zza.tgbot.ui.widget.linkBase


/**
 * @Author： created by zhangZhiAo
 * @CreateTime: 2022/8/30 16:20
 * @Describe:
 */

@Composable
fun ListPage() {
    LayoutStudy {
        Card(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
        ) {
            ConstraintLayout {
                val listScrollState = remember { mutableStateOf(false) }
                val (base, btn) = createRefs()
                Column(modifier = Modifier.constrainAs(base) { linkBase() }) {
                    PhotographerCard()
                    Conversation(msg = messageList, listScrollState) {
                        Log.e("zza", "ShowBtn$it")
                    }
                }

                IconButton(
                    modifier = Modifier
                        .constrainAs(btn) {
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)
                        }
                        .wrapContentSize(Alignment.BottomEnd)
                        .padding(end = 16.dp, bottom = 16.dp)
                        .size(28.dp),
                    onClick = {
                        listScrollState.value = true
                    }) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowUp,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(4.dp)
                            .clip(CircleShape)
                    )
                }
            }
        }

    }

}

@Composable
fun Conversation(
    msg: List<Message>,
    toTopState: MutableState<Boolean>,
    showTheBtn: (Boolean) -> Unit
) {
    AndroidView(
        factory = { RecyclerView(it) },
        update = {
            it.linear().setup {
                addType<Message>(R.layout.item_rv)
                onBind {
                    (itemView as ComposeView).setContent {
                        MessageCard(msg = getModel())
                    }
                }
            }.models = msg
        })
}
@Composable
fun TestLazyColumn(items:List<Boolean>){
    LazyColumn(){
        items(items) {
            if(it){
                TODO("A Composable")
            }else{
                TODO("B Composable")
            }
        }
    }

}

@Composable
fun MessageCard(msg: Message) {
    Row(
        Modifier
            .padding(9.dp)
    ) {
        Image(
            painter = painterResource(id = R.mipmap.msg),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
        )
        var isExpanded by remember(msg.rememberId) { mutableStateOf(msg.isShow) }
        val surfaceColor: Color by animateColorAsState(
            targetValue = if (isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface
        )
        Spacer(modifier = Modifier.width(8.dp))
        Surface(
            tonalElevation = 1.dp,
            shadowElevation = 1.dp,
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier
                .animateContentSize()
                .padding(4.dp)
                .clickable {
                    isExpanded = !isExpanded
                    msg.isShow = isExpanded
                },
            color = surfaceColor
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.padding(4.dp)
            ) {
                Text(text = msg.title, style = MaterialTheme.typography.bodyMedium)
                Text(
                    text = msg.content,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    modifier = Modifier
                        .padding(all = 4.dp)
                )
            }
        }
    }
}

data class Message(val rememberId: Int, val title: String, val content: String, var isShow: Boolean)

private val messageList = mutableListOf<Message>().apply {
    for (i in 0..90) {
        add(Message(i, "小白", "你好，我是小白\n \n $i", false))
    }
}