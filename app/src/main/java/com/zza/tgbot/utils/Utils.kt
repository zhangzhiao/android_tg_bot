package com.zza.tgbot.utils

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.text.TextUtils
import android.widget.Toast


/**
 * @Author： created by zhangZhiAo
 * @CreateTime: 2022/8/17 18:57
 * @Describe:
 */

object Utils {
    fun openProxy(context: Context,result:()->Unit) {
        if(!isWifiProxy(context)){
            val intent: Intent? =
                context.packageManager.getLaunchIntentForPackage("com.v2ray.ang")
            if (intent != null) {
                context.startActivity(intent)
            } else {
                // 没有安装要跳转的app应用，提醒一下
                Toast.makeText(
                    context,
                    "哟，赶紧下载安装这个APP吧",
                    Toast.LENGTH_LONG
                ).show()
            }
        }else{
            result()
        }
    }

    /**
     * 是否使用VPN
     *
     * @param context
     * @return true 使用vpn false 没有使用
     */
    fun isWifiProxy(context: Context): Boolean {
        val proxyPort: Int
        val proxyAddress: String? = System.getProperty("http.proxyHost")
            val portStr = System.getProperty("http.proxyPort")
            proxyPort = (portStr ?: "-1").toInt()
        return !TextUtils.isEmpty(proxyAddress) && proxyPort != -1 || checkVPN(
            context
        )
    }

    /**
     * Android判断是否使用VPN
     *
     * @param context
     * @return true 使用vpn  false 没有使用
     */
    fun checkVPN(context: Context): Boolean {
        val connectivityManager = context.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_VPN)
        return networkInfo != null && networkInfo.isConnected
    }

}