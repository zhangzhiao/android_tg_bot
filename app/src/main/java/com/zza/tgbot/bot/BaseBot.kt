package com.zza.tgbot.bot

import android.util.Log
import android.widget.Toast
import com.zza.tgbot.app.Constants
import com.zza.tgbot.manager.BotMessageManager
import com.zza.tgbot.manager.MessageDbManager
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.objects.Update


/**
 * @Author： created by zhangZhiAo
 * @CreateTime: 2022/8/1 14:51
 * @Describe: 基础bot
 */

class BaseBot constructor(var onRegisters:()->Unit={}) : TelegramLongPollingBot() {
    override fun getBotToken(): String {
        return Constants.Bot_Token
    }

    override fun getBotUsername(): String {
        return Constants.Bot_Name
    }

    override fun onRegister() {
        super.onRegister()
        Log.e("zza", "Register")
        onRegisters()

    }

    override fun onUpdateReceived(update: Update?) {
        update?.message?.let {
            MessageDbManager.resolveMessage(it)
        }

    }

}