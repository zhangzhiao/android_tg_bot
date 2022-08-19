package com.zza.tgbot.manager

import com.zza.tgbot.app.SaveKey
import com.zza.tgbot.bot.BaseBot
import com.zza.tgbot.utils.SPUtils
import org.telegram.telegrambots.meta.api.methods.GetFile
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.File
import org.telegram.telegrambots.meta.api.objects.PhotoSize
import org.telegram.telegrambots.meta.api.objects.Video
import org.telegram.telegrambots.meta.exceptions.TelegramApiException
import java.util.*
import kotlin.Comparator


/**
 * @Author： created by zhangZhiAo
 * @CreateTime: 2022/8/1 15:16
 * @Describe: 机器人发送信息管理类
 */

object BotMessageManager {
    /**
     * 选择要发送消息的bot
     */
    lateinit var bot: BaseBot

    var botIsLaunch = false

    /**
     * 变更要发送信息的bot
     */
    fun changeBot(bot: BaseBot) {
        this.bot = bot
    }

    fun sendMsg(text: String, chatId: Long = SPUtils.getLong(SaveKey.Key_ChatId)) {
        val sendMessage = SendMessage()
        sendMessage.chatId = chatId.toString()
        sendMessage.text = text
        bot.execute(sendMessage)
    }

    fun getImage(photoSizes: MutableList<PhotoSize?>): PhotoSize? {
        photoSizes.sortBy { it?.fileSize }
        return photoSizes[photoSizes.size - 1]
    }


    fun getPhotoPath(photo: PhotoSize): String? {
        Objects.requireNonNull(photo)
        if (photo.filePath != null && photo.filePath != "") {
            return photo.filePath
        } else {
            val getFileMethod = GetFile()
            getFileMethod.fileId = photo.fileId
            try {
                val file: File = bot.execute(getFileMethod)
                return file.filePath
            } catch (e: TelegramApiException) {
                e.printStackTrace()
            }
        }
        return null
    }

    fun getVideoFilePath(photo: Video): String? {
        Objects.requireNonNull(photo)
        val getFileMethod = GetFile()
        getFileMethod.fileId = photo.fileId
        try {
            val file: File = bot.execute(getFileMethod)
            return file.filePath
        } catch (e: TelegramApiException) {
            e.printStackTrace()
        }
        return null
    }

}