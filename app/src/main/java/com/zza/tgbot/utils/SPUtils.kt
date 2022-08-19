package com.zza.tgbot.utils


/**
 * @Author： created by zhangZhiAo
 * @CreateTime: 2022/6/10 15:15
 * @Describe: SP
 */


import android.content.Context
import android.os.Parcelable
import com.tencent.mmkv.MMKV


object SPUtils {
    lateinit var kv: MMKV
    fun init(context: Context?) {
        MMKV.initialize(context)
        kv = MMKV.defaultMMKV()
    }

    fun putBoolean(key: String, value: Boolean) {
        kv.encode(key, value)
    }

    fun getBoolean(key: String): Boolean {
        return kv.decodeBool(key, false)
    }

    fun getBoolean(key: String, defValue: Boolean): Boolean {
        return kv.decodeBool(key, defValue)
    }

    fun putInteger(key: String, value: Int) {
        kv.encode(key, value)
    }

    fun getInteger(key: String): Int {
        return kv.decodeInt(key, -1)
    }

    fun getInteger(key: String, defValue: Int): Int {
        return kv.decodeInt(key, defValue)
    }

    fun putString(key: String, value: String) {
        kv.encode(key, value)
    }

    fun putStringSet(key: String, value: MutableSet<String>) {
        kv.encode(key, value)
    }

    fun putLong(key: String, value: Long) {
        kv.encode(key, value)
    }

    fun getString(key: String): String {
        return kv.decodeString(key, "")!!
    }

    fun getString(key: String, defaultValue: String): String {
        return kv.decodeString(key, defaultValue)!!
    }

    fun getStringSet(key: String): MutableSet<String> {
        return kv.decodeStringSet(key, mutableSetOf<String>())!!
    }

    fun putBean(key: String, bean: Parcelable) {
        kv.encode(key, bean)
    }

    fun <T : Parcelable> getBean(key: String, t: Class<T>): T? {
        return kv.decodeParcelable(key, t)
    }

    fun getLong(key: String): Long {
        return kv.decodeLong(key, -1)
    }
}

