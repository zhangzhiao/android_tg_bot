package com.zza.tgbot.database

import androidx.room.*
import com.zza.tgbot.bean.BotEntity
import com.zza.tgbot.bean.MessageFileEntity
import com.zza.tgbot.bean.MessageUserEntity


/**
 * @Author： created by zhangZhiAo
 * @CreateTime: 2022/8/3 11:38
 * @Describe:
 */
@Dao
interface MessageUserDao {
    @Query("Select * From ${DBConfig.USER_TABLE} Where userid=:userId")
    fun getUserById(userId: Long): MutableList<MessageUserEntity>

    @Delete
    fun deleteUser(vararg messageFileEntity: MessageUserEntity)

    @Insert
    fun insertUser(vararg messageFileEntity: MessageUserEntity)

    @Insert
    fun insertAllUser(messageFileEntity: MutableList<MessageUserEntity>)

    @Update
    fun updateUser(vararg messageFileEntity: MessageUserEntity)
}