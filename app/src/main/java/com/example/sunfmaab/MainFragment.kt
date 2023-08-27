package com.example.sunfmaab

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * Created by jihengyang on 2023/8/8.
 * @mail jihengyang@bytedance.com
 */


class MainFragment(val param: String) : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("MyLog", "MainFragment onCreate, $this")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("MyLog", "MainFragment onCreateView, $this")
        return inflater.inflate(R.layout.layout_main_fragment, container, false)
    }
}