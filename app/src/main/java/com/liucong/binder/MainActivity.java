package com.liucong.binder;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {

    private IMyAidlInterface remote_interface;
    static final String TAG = "liucong";

    private IClient client_interface = new IClient.Stub() {
        @Override
        public MyData getData() throws RemoteException {
            Log.d(TAG, "get data");
            MyData data = new MyData();
            data.setA(5);
            data.setB(6);
            return data;
        }
    };

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(TAG, "onServiceConnected");
            remote_interface = IMyAidlInterface.Stub.asInterface(iBinder);
            try {
                MyData data = remote_interface.getMyData();
                Log.d(TAG, "a:" + data.getA() + "|b:" + data.getB());
                Log.d(TAG, "transport");
                remote_interface.transport(client_interface);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(TAG, "onServiceDisconnected");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void bind(View view) {
        Log.d(TAG, "bind");
        Intent intent = new Intent(this, MyService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    public void unbind(View view) {
        Log.d(TAG, "unbind");
        unbindService(connection);
    }

    public void kill(View view) {
        try {
            int pid = remote_interface.getpid();
            Log.d(TAG, "kill " + pid);
            android.os.Process.killProcess(pid);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void server_call_client(View view) {
        try {
            remote_interface.transport(client_interface);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private Intent service2_intent = null;

    public void start_service2(View view) {
        service2_intent = new Intent(this, MyService2.class);
        startService(service2_intent);
    }

    public void stop_service2(View view) {
        if (service2_intent != null)
            stopService(service2_intent);
        service2_intent = null;
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Log.d(TAG,"handlerMessage",new Throwable());
            super.handleMessage(msg);
        }
    };

    public void send_broadcast(View view) {
        Log.d(TAG, "send_broadcast");
        Intent intent = new Intent("com.liucong.MyReceiver");
        sendBroadcast(intent);
        Message message = Message.obtain();
        handler.sendMessage(message);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume", new Throwable());
    }
}
