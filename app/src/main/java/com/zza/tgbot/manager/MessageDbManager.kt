package com.zza.tgbot.manager

import android.util.Log
import com.zza.tgbot.bean.MessageChatEntity
import com.zza.tgbot.bean.MessageFileEntity
import com.zza.tgbot.database.DatabaseManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.PhotoSize
import java.util.*
import java.util.concurrent.LinkedBlockingQueue
import kotlin.concurrent.thread

/**
 * 数据库管理类
 */
object MessageDbManager {
    /**
     * 处理文件消息
     */
    private val fileQueue: MessageQueue<MessageFileEntity> = MessageQueue()

    fun resolveMessage(message: Message) {
        var messageType = MessageChatEntity.MessageChatType.TEXT.ordinal
        //1. 判断存在不存在文件
        message.video?.let {
            val temp = MessageFileEntity(
                fileId = it.fileId,
                fileUniqueId = it.fileUniqueId,
                fileName = it.fileName,
                mimeType = it.mimeType,
                duration = it.duration
            )
            messageType = MessageChatEntity.MessageChatType.VIDEO.ordinal
            saveFileQueue(temp)
            it.thumb?.let { photoSize ->
                val tempPhotoSize = MessageFileEntity(
                    fileId = photoSize.fileId,
                    fileUniqueId = photoSize.fileUniqueId,
                    filePath = photoSize.filePath,
                    width = photoSize.width,
                    height = photoSize.height
                )
                saveFileQueue(tempPhotoSize)
            }

        }
        message.document?.let {
            val temp = MessageFileEntity(
                fileId = it.fileId,
                fileUniqueId = it.fileUniqueId,
                fileName = it.fileName,
                mimeType = it.mimeType
            )
            messageType = MessageChatEntity.MessageChatType.DOCUMENT.ordinal
            saveFileQueue(temp)
            it.thumb?.let { photoSize ->
                val tempPhotoSize = MessageFileEntity(
                    fileId = photoSize.fileId,
                    fileUniqueId = photoSize.fileUniqueId,
                    filePath = photoSize.filePath,
                    width = photoSize.width,
                    height = photoSize.height
                )
                saveFileQueue(tempPhotoSize)
            }
        }
        message.voice?.let {
            val temp = MessageFileEntity(
                fileId = it.fileId,
                fileUniqueId = it.fileUniqueId,
                mimeType = it.mimeType
            )
            messageType = MessageChatEntity.MessageChatType.VOICE.ordinal
            saveFileQueue(temp)
        }
        //2. 判断存在不存在图片
        message.photo?.let {
            messageType = MessageChatEntity.MessageChatType.PHOTO.ordinal
            for (photoSize in it) {
                val tempPhotoSize = MessageFileEntity(
                    fileId = photoSize.fileId,
                    fileUniqueId = photoSize.fileUniqueId,
                    filePath = photoSize.filePath,
                    width = photoSize.width,
                    height = photoSize.height
                )
                saveFileQueue(tempPhotoSize)
            }
        }




    }

    private fun saveFileQueue(messageFile: MessageFileEntity) {
        fileQueue.addQueue(messageFile)
    }

    fun initQueue() {
        thread {
            fileQueue.initQueue {
                DatabaseManager.messageFileDb.fileDao.insertFile(it)
            }
        }
    }

    /**
     * 消息保存队列
     */
    class MessageQueue<T> {
        private val messageQueue = LinkedBlockingQueue<T>()
        fun addQueue(message: T) {
            messageQueue.put(message)
        }

        fun initQueue(save: (t: T) -> Unit) {
            while (true) {
                save(messageQueue.take())
            }
        }
    }

}