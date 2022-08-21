package com.zza.tgbot.database

import androidx.room.*
import com.zza.tgbot.bean.BotEntity
import com.zza.tgbot.bean.MessageFileEntity


/**
 * @Author： created by zhangZhiAo
 * @CreateTime: 2022/8/3 11:38
 * @Describe:
 */
@Dao
interface MessageFileDao {
    @Query("Select * From ${DBConfig.FILE_TABLE} Where fileId =:fileId & fileUniqueId=:fileUniqueId")
    fun getFileById(fileId: String, fileUniqueId: String): MutableList<MessageFileEntity>

    @Delete
    fun deleteFile(vararg messageFileEntity: MessageFileEntity)

    @Insert
    fun insertFile(vararg messageFileEntity: MessageFileEntity)

    @Insert
    fun insertAllFile(messageFileEntity: MutableList<MessageFileEntity>)

    @Update
    fun updateFile(vararg messageFileEntity: MessageFileEntity)
}