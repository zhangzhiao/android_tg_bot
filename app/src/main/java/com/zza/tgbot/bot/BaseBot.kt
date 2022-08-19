package com.zza.tgbot.bot

import android.util.Log
import com.zza.tgbot.app.Constants
import com.zza.tgbot.manager.BotMessageManager
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.objects.Update


/**
 * @Author： created by zhangZhiAo
 * @CreateTime: 2022/8/1 14:51
 * @Describe: 基础bot
 */

class BaseBot : TelegramLongPollingBot() {
    override fun getBotToken(): String {
        return Constants.Bot_Token
    }

    override fun getBotUsername(): String {
        return Constants.Bot_Name
    }

    override fun onUpdateReceived(update: Update?) {
        Log.e("zza", "onUpdateReceived: ${update?.message?.toString()}")
        update?.message?.photo?.let {
            BotMessageManager.getImage(it)
                ?.let { it1 ->
                    Log.e(
                        "zza",
                        "photo${this.getFileUrl(BotMessageManager.getPhotoPath(it1))}"
                    )
                }
        }
        Log.e("zzaIcon", "icon${this.getFileUrl(update?.message?.chat?.photo?.toString())}")

    }

}