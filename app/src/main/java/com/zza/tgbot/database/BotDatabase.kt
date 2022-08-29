package com.zza.tgbot.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zza.tgbot.bean.BotEntity
import com.zza.tgbot.bean.MessageChatEntity
import com.zza.tgbot.bean.MessageFileEntity
import com.zza.tgbot.bean.MessageUserEntity


/**
 * @Author： created by zhangZhiAo
 * @CreateTime: 2022/8/3 11:51
 * @Describe: bot数据库
 */

@Database(version = 1, exportSchema = false, entities = [BotEntity::class])
abstract class BotDatabase : RoomDatabase() {
    val botDao by lazy { createDao() }
    abstract fun createDao(): BotDao
}

@Database(version = 1, exportSchema = false, entities = [MessageFileEntity::class])
abstract class MessageFileDatabase : RoomDatabase() {
    val fileDao by lazy { createDao() }
    abstract fun createDao(): MessageFileDao

    fun insertOfReplace(messageFileEntity: MessageFileEntity) {
        if (fileDao.getFileById(messageFileEntity.fileId, messageFileEntity.fileUniqueId)
                .isEmpty()
        ) {
            fileDao.insertFile(messageFileEntity)
        } else {
            fileDao.updateFile(messageFileEntity)
        }

    }
}

@Database(version = 1, exportSchema = false, entities = [MessageChatEntity::class])
abstract class MessageChatDatabase : RoomDatabase() {
    val chatDao by lazy { createDao() }
    abstract fun createDao(): MessageChatDao
    fun insertOfReplace(messageChatEntity: MessageChatEntity) {
        if (chatDao.getChatByUserIdAndMsgId(messageChatEntity.userId, messageChatEntity.messageId)
                .isEmpty()
        ) {
            chatDao.insertChat(messageChatEntity)
        } else {
            chatDao.updateChat(messageChatEntity)
        }

    }
}

@Database(version = 1, exportSchema = false, entities = [MessageUserEntity::class])
abstract class MessageUserDatabase : RoomDatabase() {
    val userDao by lazy { createDao() }
    abstract fun createDao(): MessageUserDao
    fun insertOfReplace(messageUserEntity: MessageUserEntity) {
        if (userDao.getUserById(messageUserEntity.userid)
                .isEmpty()
        ) {
            userDao.insertUser(messageUserEntity)
        } else {
            userDao.updateUser(messageUserEntity)
        }

    }
}