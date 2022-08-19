package com.zza.tgbot.listener

import org.telegram.telegrambots.meta.api.objects.Update


/**
 * @Author： created by zhangZhiAo
 * @CreateTime: 2022/8/17 17:16
 * @Describe:
 */

interface BotMessageReceivedListener {
    fun onReceived(update: Update?)
}