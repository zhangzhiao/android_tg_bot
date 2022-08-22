package com.zza.tgbot.bean

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.zza.tgbot.database.DBConfig
import java.time.Duration

@Entity(tableName = DBConfig.FILE_TABLE)
data class MessageFileEntity @JvmOverloads constructor(
    @PrimaryKey(autoGenerate = true)
    var dbId: Long = 0L,
    var fileId: String,
    var fileUniqueId: String,
    var fileName: String = "",
    var mimeType: String = "",
    var filePath: String? = null,
    var duration: Int =0,
    var width: Int = 0,
    var height: Int = 0
) {
}
