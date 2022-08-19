package com.zza.tgbot.ui.widget

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.zza.tgbot.R


/**
 * @Author： created by zhangZhiAo
 * @CreateTime: 2022/8/1 15:46
 * @Describe:
 */

class TitleBar @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null) :
    ConstraintLayout(context, attributeSet) {
    var titleText: String? = null
    var leftIcon: Drawable? = null
    val titleLeftIcon by lazy { findViewById<ImageView>(R.id.titleLeftIcon) }
    val titleTextView by lazy { findViewById<TextView>(R.id.titleText) }

    init {
        LayoutInflater.from(context).inflate(R.layout.layout_titlebar, this, true)
        val ta = context.obtainStyledAttributes(attributeSet, R.styleable.TitleBar, 0, 0)
        titleText = ta.getString(R.styleable.TitleBar_title)
        leftIcon = ta.getDrawable(R.styleable.TitleBar_leftIcon)
        ta.recycle()
        titleTextView.text = titleText
        titleLeftIcon.setImageDrawable(leftIcon)
    }

    fun setTitleTextStr(text: String) {
        titleText = text
        titleTextView.text = text
    }

    fun setLeftIconClick(block: () -> Unit) {
        titleLeftIcon.setOnClickListener { block() }
    }
}