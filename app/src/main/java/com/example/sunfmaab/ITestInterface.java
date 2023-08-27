package com.example.sunfmaab;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by jihengyang on 2023/8/14.
 *
 * @mail jihengyang@bytedance.com
 */
public interface ITestInterface {
    static void testName(Context context) {
        Toast.makeText(context, "testName", Toast.LENGTH_SHORT).show();
    }
}
