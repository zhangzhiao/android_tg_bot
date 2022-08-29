package com.zza.tgbot.manager

import android.util.Log
import com.zza.tgbot.bean.MessageChatEntity
import com.zza.tgbot.bean.MessageChatType
import com.zza.tgbot.bean.MessageFileEntity
import com.zza.tgbot.bean.MessageUserEntity
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

    /**
     * 处理消息
     */
    private val chatQueue: MessageQueue<MessageChatEntity> = MessageQueue()

    /**
     * 更新用户消息
     */
    private val userQueue: MessageQueue<MessageUserEntity> = MessageQueue()


    fun resolveMessage(message: Message) {
        var messageType = MessageChatType.TEXT.ordinal
        //判断文件类型
        message.video?.let {
            val temp = MessageFileEntity(
                fileId = it.fileId,
                messageId = message.messageId,
                fileUniqueId = it.fileUniqueId,
                fileName = it.fileName,
                mimeType = it.mimeType,
                duration = it.duration
            )
            messageType = MessageChatType.VIDEO.ordinal
            saveFileQueue(temp)
            it.thumb?.let { photoSize ->
                val tempPhotoSize = MessageFileEntity(
                    fileId = photoSize.fileId,
                    messageId = message.messageId,
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
                messageId = message.messageId,
                fileUniqueId = it.fileUniqueId,
                fileName = it.fileName,
                mimeType = it.mimeType
            )
            messageType = MessageChatType.DOCUMENT.ordinal
            saveFileQueue(temp)
            it.thumb?.let { photoSize ->
                val tempPhotoSize = MessageFileEntity(
                    fileId = photoSize.fileId,
                    fileUniqueId = photoSize.fileUniqueId,
                    filePath = photoSize.filePath,
                    width = photoSize.width,
                    messageId = message.messageId,
                    height = photoSize.height
                )
                saveFileQueue(tempPhotoSize)
            }
        }
        message.voice?.let {
            val temp = MessageFileEntity(
                fileId = it.fileId,
                messageId = message.messageId,
                fileUniqueId = it.fileUniqueId,
                mimeType = it.mimeType
            )
            messageType = MessageChatType.VOICE.ordinal
            saveFileQueue(temp)
        }
        message.photo?.let {
            messageType = MessageChatType.PHOTO.ordinal
            for (photoSize in it) {
                val tempPhotoSize = MessageFileEntity(
                    fileId = photoSize.fileId,
                    messageId = message.messageId,
                    fileUniqueId = photoSize.fileUniqueId,
                    filePath = photoSize.filePath,
                    width = photoSize.width,
                    height = photoSize.height
                )
                saveFileQueue(tempPhotoSize)
            }
        }
        val messageEntity = MessageChatEntity(
            userId = message.chat.id,
            date = message.date,
            messageId = message.messageId,
            type = messageType,
            text = message.text
        )
    }

    private fun saveChatQueue(messageChatEntity: MessageChatEntity) {
        chatQueue.addQueue(messageChatEntity)
    }

    private fun saveUserQueue(messageUserEntity: MessageUserEntity) {
        userQueue.addQueue(messageUserEntity)
    }

    private fun saveFileQueue(messageFile: MessageFileEntity) {
        fileQueue.addQueue(messageFile)
    }

    fun initQueue() {
        thread {
            fileQueue.initQueue {
                DatabaseManager.messageFileDb.insertOfReplace(it)
            }
        }
        thread {
            chatQueue.initQueue {
                DatabaseManager.messageChatDb.insertOfReplace(it)
            }
        }
        thread {
            userQueue.initQueue {
                DatabaseManager.messageUserDb.insertOfReplace(it)
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