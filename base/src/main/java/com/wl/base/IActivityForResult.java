package com.wl.base;

import android.app.Activity;
import android.content.Intent;

public interface IActivityForResult {
    void startActivityForResult(Class<? extends Activity> cls, ActivityCallback callback);

    void startActivityForResult(Intent intent, ActivityCallback callback);

    Activity getCurrentActivity();
}
