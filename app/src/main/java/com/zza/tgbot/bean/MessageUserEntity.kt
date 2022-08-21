package com.zza.tgbot.bean

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.zza.tgbot.database.DBConfig

//聊天用户表
@Entity(tableName = DBConfig.USER_TABLE)
data class MessageUserEntity @JvmOverloads constructor(
    @PrimaryKey(autoGenerate = true)
    var dbId:Long=0L,
    var userid:String,
    var firstName:String,
    var lastName:String,
    var userName:String,
    var canJobGroups:String,
    //最后一条消息的ChatId
    var lastChatId:String,
    //用户对于该对象的剩余未读消息
    var lastChatNotReadCount:Int
) {
    /**
     * User(id=1604834725, firstName=z, isBot=false, lastName=zza,
     * userName=Xiaoaoao_acc,
     * languageCode=zh-hans, canJoinGroups=null,
     * canReadAllGroupMessages=null, supportInlineQueries=null,
     * isPremium=null, addedToAttachmentMenu=null)
     */

}