package com.liucong.binder;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService2 extends Service {
    public MyService2() {
    }

    @Override
    public void onCreate() {
        Log.d(MainActivity.TAG, "onCreate", new Throwable());
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(MainActivity.TAG, "onStartCommand", new Throwable());
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d(MainActivity.TAG, "onDestroy", new Throwable());
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
