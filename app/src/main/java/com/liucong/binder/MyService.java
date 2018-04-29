package com.liucong.binder;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class MyService extends Service {

    private static final String TAG = "liucong";

    private IMyAidlInterface.Stub mBinder = new IMyAidlInterface.Stub() {
        @Override
        public int getpid() throws RemoteException {
            Log.d(TAG, "getpid");
            return android.os.Process.myPid();
        }

        @Override
        public MyData getMyData() throws RemoteException {
            Log.d(TAG, "getMyData");
            MyData data = new MyData();
            data.setA(1);
            data.setB(2);
            return data;
        }

        @Override
        public void transport(IClient client) throws RemoteException {
            Log.d(TAG, "tranport in MyService");
            //IClient remote_interface = IClient.Stub.asInterface(client);
            //MyData data = remote_interface.getData();
            MyData data = client.getData();
            Log.d(TAG, "a:" + data.getA() + "|b:" + data.getB());
        }
    };

    public MyService() {
    }

    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind intent:" + intent);
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
    }
}
