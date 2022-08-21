package com.zza.tgbot.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zza.tgbot.bean.BotEntity
import com.zza.tgbot.bean.MessageFileEntity


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
}