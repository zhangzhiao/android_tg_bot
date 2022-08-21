package com.zza.tgbot.bean

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.zza.tgbot.database.DBConfig


/**
 * @Author： created by zhangZhiAo
 * @CreateTime: 2022/8/3 11:32
 * @Describe: 机器人实体类
 */

@Entity(tableName = DBConfig.BOT_TABLE)
data class BotEntity @JvmOverloads constructor(
    @PrimaryKey(autoGenerate = true)
    var itemId: Long = 0L,
    var token: String,
    var name: String,
    var remarks: String
)