package com.zza.tgbot.app

import android.app.Application
import com.drake.net.NetConfig
import com.drake.net.okhttp.setDebug
import com.zza.tgbot.database.DatabaseManager
import com.zza.tgbot.manager.MessageDbManager
import com.zza.tgbot.utils.SPUtils


/**
 * @Author： created by zhangZhiAo
 * @CreateTime: 2022/8/1 15:21
 * @Describe:
 */

class BotApplication : Application() {
    //    1604834725
    companion object {
        lateinit var botApplication: BotApplication
    }

    override fun onCreate() {
        super.onCreate()
        SPUtils.init(this)
        DatabaseManager.initApplication(this)
        botApplication = this
        MessageDbManager.initQueue()
        NetConfig.initialize {
            setDebug(true)
        }
    }

}