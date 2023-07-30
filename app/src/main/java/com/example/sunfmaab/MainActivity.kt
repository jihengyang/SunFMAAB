package com.example.sunfmaab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        PluginLoadingDialog(this).apply {
            setCanceledOnTouchOutside(true)
        }.show()
    }
}