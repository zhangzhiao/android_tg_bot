package com.zza.tgbot.database

import androidx.room.*
import com.zza.tgbot.bean.BotEntity


/**
 * @Author： created by zhangZhiAo
 * @CreateTime: 2022/8/3 11:38
 * @Describe:
 */
@Dao
interface BotDao {
    @Query("Select * From bot")
    fun getAllBot(): MutableList<BotEntity>

    @Query("Select * From bot Where name =:name")
    fun getBotByName(name: String): MutableList<BotEntity>

    @Delete
    fun deleteBot(vararg bot: BotEntity)

    @Insert
    fun insertBot(vararg bot: BotEntity)

    @Insert
    fun insertAllBot(bots: MutableList<BotEntity>)

    @Update
    fun updateBot(vararg bot: BotEntity)
}