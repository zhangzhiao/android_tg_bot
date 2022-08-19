package com.zza.tgbot.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gyf.immersionbar.ImmersionBar


/**
 * @Author： created by zhangZhiAo
 * @CreateTime: 2022/8/1 15:28
 * @Describe:
 */

open class BaseActivity :AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ImmersionBar.with(this).init()
    }
}