// IMyAidlInterface.aidl
package com.liucong.binder;
import com.liucong.binder.MyData;
import com.liucong.binder.IClient;
// Declare any non-default types here with import statements

interface IMyAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    int getpid();
    MyData getMyData();
    void transport(IClient client);
}
