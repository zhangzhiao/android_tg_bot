package com.zza.tgbot.database

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.zza.tgbot.app.BotApplication


/**
 * @Author： created by zhangZhiAo
 * @CreateTime: 2022/8/3 12:01
 * @Describe: 数据库管理类
 */

object DatabaseManager {
    private const val DB_Name = "bot.db"
    private val MIGRATIONS = arrayOf(Migration1)
    private lateinit var botApplication: BotApplication
    val db: BotDatabase by lazy {
        Room.databaseBuilder(botApplication.applicationContext, BotDatabase::class.java, DB_Name)
            .addCallback(CreatedCallBack)
            .addMigrations(*MIGRATIONS)
            .build()
    }

    fun initApplication(application: BotApplication) {
        botApplication = application
    }

    private object CreatedCallBack : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            //在新装app时会调用，调用时机为数据库build()之后，数据库升级时不调用此函数
            MIGRATIONS.map {
                it.migrate(db)
            }
        }
    }

    private object Migration1 : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            // 数据库的升级语句
            // database.execSQL("")
        }
    }
}