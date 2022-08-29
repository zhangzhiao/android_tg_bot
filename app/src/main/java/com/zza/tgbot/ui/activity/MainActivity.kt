package com.zza.tgbot.ui.activity

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.drake.net.Get
import com.drake.net.Net
import com.drake.net.utils.scopeNetLife
import com.zza.tgbot.R
import com.zza.tgbot.base.BaseActivity
import com.zza.tgbot.bot.BaseBot
import com.zza.tgbot.manager.BotMessageManager
import com.zza.tgbot.manager.BotMessageManager.botIsLaunch
import com.zza.tgbot.utils.Utils
import org.slf4j.helpers.Util
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.meta.exceptions.TelegramApiException
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession
import kotlin.concurrent.thread


class MainActivity : BaseActivity() {
    var isShowText = false
    lateinit var text1: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        text1 = findViewById(R.id.text1)
        text1.setOnClickListener {
            isShowText = !isShowText
            if (isShowText) {
                text1.maxLines = Int.MAX_VALUE
            } else {
                text1.maxLines = 1
            }
        }
    }

    override fun onResume() {
        super.onResume()
//        if (!botIsLaunch) {
//            Utils.openProxy(this) {
//                Toast.makeText(this, "启动TG", Toast.LENGTH_SHORT).show()
//                Thread {
//                    testTg()
//                }.start()
//            }
//        }
    }

    private fun testTg() {
        val defaultBotSession = DefaultBotSession()
        val telegramBotsApi = TelegramBotsApi(defaultBotSession.javaClass)
        val baseBot = BaseBot {
            runOnUiThread {
                Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show()
            }
        }
        try {
            telegramBotsApi.registerBot(baseBot)
            BotMessageManager.changeBot(baseBot)
        } catch (e: TelegramApiException) {
            Log.e("zza", "注册失败", e)
        }
        botIsLaunch = true
    }

    override fun onDestroy() {
        super.onDestroy()
        BotMessageManager.closeBot()
    }
}