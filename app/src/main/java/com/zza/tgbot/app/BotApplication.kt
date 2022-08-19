package com.zza.tgbot.app

import android.app.Application
import com.zza.tgbot.database.DatabaseManager
import com.zza.tgbot.utils.SPUtils


/**
 * @Author： created by zhangZhiAo
 * @CreateTime: 2022/8/1 15:21
 * @Describe:
 */

class BotApplication : Application() {
//    1604834725
    override fun onCreate() {
        super.onCreate()
        SPUtils.init(this)
        DatabaseManager.initApplication(this)
    }
}