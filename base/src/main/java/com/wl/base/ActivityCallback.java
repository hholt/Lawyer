package com.wl.base;

import android.content.Intent;

import androidx.annotation.Nullable;

/**
 * Activity 回调接口
 */
public interface ActivityCallback {

    /**
     * 结果回调
     *
     * @param resultCode 结果码
     * @param data       数据
     */
    void onActivityResult(int resultCode, @Nullable Intent data);
}
