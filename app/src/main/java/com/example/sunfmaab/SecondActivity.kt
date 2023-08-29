package com.example.sunfmaab

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val oriIntent = intent.getParcelableExtra<Intent>("originIntent")
        Log.i("SecondActivity", "onCreate")
    }
}