package com.example.sunfmaab

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest

class MainActivity : AppCompatActivity() {
    private val handler by lazy {
        Handler(HandlerThread("MainActivity").run {
            start()
            looper
        })
    }

    private val handlerThread = HandlerThread("").apply {
        start()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        PluginLoadingDialog(this).apply {
//            setCanceledOnTouchOutside(true)
//        }.show()
        findViewById<View>(R.id.center_btn).setOnClickListener {
            val request =
                SplitInstallRequest
                    .newBuilder()
                    .addModule("livedynamicfeature")
                    .build()

            val splitInstallManager = SplitInstallManagerFactory.create(this@MainActivity)
            splitInstallManager
                .startInstall(request)
                .addOnSuccessListener { sessionId ->
                    Log.i("MyLog", "success")
                    Intent(
                        this@MainActivity,
                        Class.forName("com.example.livedynamicfeature.LivePlayerActivity")
                    ).run {
                        startActivity(this)
                    }
                }
                .addOnFailureListener { exception ->
                    Log.i("MyLog", "failed")
                }
                .addOnCompleteListener {
                    Log.i("MyLog", "complete")
                }
        }
        Log.i("MyLog", "MainActivity onCreate, $this")
//        supportFragmentManager.beginTransaction()
//            .add(R.id.fragment_container, MainFragment("")).commitAllowingStateLoss()
//
//        handler.post {
//            Log.i("MyLog", "thread, ${Thread.currentThread().name}")
//            WebView(this@MainActivity)
//        }
//        ITestInterface.testName(this)
    }
}

private class CustomHandlerThread(name: String?) : HandlerThread(name)