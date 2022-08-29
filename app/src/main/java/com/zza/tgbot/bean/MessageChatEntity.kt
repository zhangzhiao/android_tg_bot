package com.zza.tgbot.bean

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.zza.tgbot.database.DBConfig

/**
 * 聊天信息 消息内容表
 */
@Entity(tableName = DBConfig.CHAT_TABLE)
data class MessageChatEntity constructor(
    @PrimaryKey(autoGenerate = true)
    var dbId: Long = 0L,
    var userId: Long,
    var messageId: Int,
    var date: Int,
    //消息中的文本
    var text: String,
    var type: Int
) {

    /**
     * Message(messageId=567, date=1660808807,
     * chat=Chat(id=1604834725, type=private, title=null, firstName=z, lastName=zza, userName=Xiaoaoao_acc, photo=null, description=null, inviteLink=null,
     * pinnedMessage=null, stickerSetName=null, canSetStickerSet=null, permissions=null, slowModeDelay=null, bio=null, linkedChatId=null, location=null,
     * messageAutoDeleteTime=null, hasPrivateForwards=null, HasProtectedContent=null, joinToSendMessages=null, joinByRequest=null)
     * , forwardFrom=null, forwardFromChat=null, forwardDate=null, text=null, entities=null, captionEntities=null, audio=null,
     * document=Document(
     * fileId=CgACAgUAAxkBAAICN2L97meq-GpvWq-LJQffSabm2_DxAAIcCAACjbDwV3e7EcbdOmcCKQQ, fileUniqueId=AgADHAgAAo2w8Fc,
     * thumb=PhotoSize(fileId=AAMCBQADGQEAAgI3Yv3uZ6r4am9ar4slB99Jpubb8PEAAhwIAAKNsPBXd7sRxt06ZwIBAAdtAAMpBA,
     * fileUniqueId=AQADHAgAAo2w8Fdy, width=244, height=136, fileSize=11342, filePath=null), fileName=animation.mp4,
     * mimeType=video/mp4, fileSize=216384), photo=null, sticker=null, video=null, contact=null, location=null, venue=null,
     * animation=Animation(fileId=CgACAgUAAxkBAAICN2L97meq-GpvWq-LJQffSabm2_DxAAIcCAACjbDwV3e7EcbdOmcCKQQ, fileUniqueId=AgADHAgAAo2w8Fc,
     * width=244, height=136, duration=3, thumb=PhotoSize(fileId=AAMCBQADGQEAAgI3Yv3uZ6r4am9ar4slB99Jpubb8PEAAhwIAAKNsPBXd7sRxt06ZwIBAAdtAAMpBA,
     * fileUniqueId=AQADHAgAAo2w8Fdy, width=244, height=136, fileSize=11342, filePath=null), fileName=animation.mp4, mimetype=video/mp4, fileSize=216384),
     * pinnedMessage=null, newChatMembers=[], leftChatMember=null, newChatTitle=null, newChatPhoto=null, deleteChatPhoto=null, groupchatCreated=null,
     * replyToMessage=null, voice=null, caption=null, superGroupCreated=null, channelChatCreated=null, migrateToChatId=null, migrateFromChatId=null,
     * editDate=null, game=null, forwardFromMessageId=null, invoice=null, successfulPayment=null, videoNote=null, authorSignature=null, forwardSignature=null,
     * mediaGroupId=null, connectedWebsite=null, passportData=null, forwardSenderName=null, poll=null, replyMarkup=null, dice=null, viaBot=null,
     * senderChat=null, proximityAlertTriggered=null, messageAutoDeleteTimerChanged=null, isAutomaticForward=null, hasProtectedContent=null,
     * webAppData=null, videoChatStarted=null, videoChatEnded=null, videoChatParticipantsInvited=null, videoChatScheduled=null)
     */
}
