// IClient.aidl
package com.liucong.binder;
import com.liucong.binder.MyData;

// Declare any non-default types here with import statements

interface IClient {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    MyData getData();
}
