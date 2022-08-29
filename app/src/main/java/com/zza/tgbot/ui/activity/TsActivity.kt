package com.zza.tgbot.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.zza.tgbot.R
import com.zza.tgbot.ui.theme.TgBotTheme
import com.zza.tgbot.ui.widget.LayoutStudy
import com.zza.tgbot.ui.widget.PhotographerCard
import kotlinx.coroutines.launch

class TsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TgBotTheme {
                LayoutStudy {
                    Card(
                        modifier = Modifier
                            .padding(4.dp)
                            .fillMaxWidth()
                    ) {
                        ConstraintLayout {
                            val listScrollState = remember { mutableStateOf(false) }
                            val (base, btn) = createRefs()
                            Column(modifier = Modifier.constrainAs(base) {
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }) {
                                PhotographerCard()
                                Conversation(msg = messageList, listScrollState)
                            }

                            IconButton(
                                modifier = Modifier
                                    .constrainAs(btn) {
                                        end.linkTo(parent.end)
                                        bottom.linkTo(parent.bottom)
                                    }
                                    .wrapContentSize(Alignment.BottomEnd)
                                    .padding(end = 16.dp, bottom = 16.dp)
                                    .background(MaterialTheme.colors.surface)
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
        }
    }

    @Composable
    fun MessageCard(msg: Message) {
        Row(
            Modifier
                .padding(9.dp)
                .background(MaterialTheme.colors.background)
        ) {
            Image(
                painter = painterResource(id = R.mipmap.msg),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
            )
            var isExpanded by remember { mutableStateOf(false) }
            val surfaceColor: Color by animateColorAsState(
                targetValue = if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(text = msg.title, color = MaterialTheme.colors.secondaryVariant)
                Spacer(modifier = Modifier.height(10.dp))
                Surface(
                    shape = MaterialTheme.shapes.medium,
                    elevation = 1.dp,
                    modifier = Modifier
                        .animateContentSize()
                        .padding(1.dp),
                    color = surfaceColor
                ) {
                    Text(
                        text = msg.content,
                        style = MaterialTheme.typography.body2,
                        maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                        modifier = Modifier
                            .padding(all = 4.dp)
                            .clickable {
                                isExpanded = !isExpanded
                            }
                    )
                }
            }
        }
    }

    @Composable
    fun Conversation(msg: List<Message>, toTopState: MutableState<Boolean>) {
        val scrollState = rememberLazyListState()
        LaunchedEffect(toTopState.value) {
            if(toTopState.value){
                Log.e("zza","animateScrollToItem")
                scrollState.animateScrollToItem(0)
                toTopState.value = false
            }
        }
        LazyColumn(state = scrollState) {
            items(msg) { message ->
                MessageCard(msg = message)
            }
        }
    }

    @Preview
    @Composable
    fun PreviewMessageCard() {
        MessageCard(Message("Android", "Jetpack Compose"))
    }

    data class Message(val title: String, val content: String)

    private val messageList = mutableListOf<Message>().apply {
        for (i in 0..100) {
            add(Message("小白", "你好，我是小白\n \n $i"))
        }
    }
}