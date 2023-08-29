package com.example.sunfmaab

import android.app.Application
import android.content.Context
import com.bytedance.keva.KevaBuilder
import com.google.android.play.core.splitcompat.SplitCompat

/**
 * Created by jihengyang on 2023/8/28.
 * @mail jihengyang@bytedance.com
 */
class MyApplication : Application() {
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)

        // Keva init if not called before by Application.
        val builder = KevaBuilder.getInstance()
        builder.setContext(base)
        SplitCompat.install(this)
    }
}