package com.example.sunfmaab

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.ViewGroup
import android.view.WindowManager

/**
 * Created by jihengyang on 2023/7/19.
 * @mail jihengyang@bytedance.com
 */
class PluginLoadingDialog(context: Context) :
    Dialog(context) {

    private val handler by lazy { Handler(Looper.getMainLooper()) }

    private var progress = 0

    private val run by lazy {
        Runnable {
            refresh()
        }
    }

    private fun refresh() {
        progress = ++progress % 100
        findViewById<TextRoundProgress>(R.id.iv_loading).progress = progress
        handler.postDelayed(run, 100)
    }

    override fun onStart() {
        super.onStart()
        window?.run {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            setBackgroundDrawableResource(android.R.color.transparent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.learning_live_progressdialog_layout)
        handler.postDelayed(run, 100)
    }


}