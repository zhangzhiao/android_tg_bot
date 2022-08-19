package com.zza.tgbot.ui.activity

import android.os.Bundle
import android.widget.Toast
import com.zza.tgbot.R
import com.zza.tgbot.base.BaseActivity
import com.zza.tgbot.bot.BaseBot
import com.zza.tgbot.manager.BotMessageManager
import com.zza.tgbot.manager.BotMessageManager.botIsLaunch
import com.zza.tgbot.utils.Utils
import org.slf4j.helpers.Util
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession


class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
    }

    override fun onResume() {
        super.onResume()
        if (!botIsLaunch) {
            Utils.openProxy(this) {
                Toast.makeText(this, "启动TG", Toast.LENGTH_SHORT).show()
                Thread {
                    testTg()
                }.start()
            }
        }
    }

    private fun testTg() {
        val defaultBotSession = DefaultBotSession()
        val telegramBotsApi = TelegramBotsApi(defaultBotSession.javaClass)
        val baseBot = BaseBot()
        telegramBotsApi.registerBot(baseBot)
        BotMessageManager.changeBot(baseBot)
        botIsLaunch = true
    }
}