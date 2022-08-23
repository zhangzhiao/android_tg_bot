package com.zza.tgbot.database

import androidx.room.*
import com.zza.tgbot.bean.BotEntity
import com.zza.tgbot.bean.MessageChatEntity
import com.zza.tgbot.bean.MessageFileEntity
import com.zza.tgbot.bean.MessageUserEntity


/**
 * @Author： created by zhangZhiAo
 * @CreateTime: 2022/8/3 11:38
 * @Describe:
 */
@Dao
interface MessageChatDao {
    @Query("Select * From ${DBConfig.CHAT_TABLE} Where userId =:userId Limit :start,:end")
    fun getChatByUserIdLimit(userId: String, start: Int, end: Int): MutableList<MessageChatEntity>

    @Query("Select * From ${DBConfig.CHAT_TABLE} Where userId =:userId and messageId =:messageId")
    fun getChatByUserIdAndMsgId(userId: Long, messageId: Int): MutableList<MessageChatEntity>

    @Delete
    fun deleteChat(vararg messageChatEntity: MessageChatEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertChat(vararg messageChatEntity: MessageChatEntity)

    @Insert
    fun insertAllChat(messageChatEntity: MutableList<MessageChatEntity>)

    @Update
    fun updateChat(vararg messageChatEntity: MessageChatEntity)
}