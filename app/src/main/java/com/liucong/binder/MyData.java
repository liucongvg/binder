package com.liucong.binder;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by liucong on 18-3-22.
 */

public class MyData implements Parcelable {

    private int a;
    private int b;

    public MyData() {

    }

    protected MyData(Parcel in) {
        readFromParcel(in);
    }

    public static final Creator<MyData> CREATOR = new Creator<MyData>() {
        @Override
        public MyData createFromParcel(Parcel in) {
            return new MyData(in);
        }

        @Override
        public MyData[] newArray(int size) {
            return new MyData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(a);
        parcel.writeInt(b);
    }

    public void readFromParcel(Parcel parcel) {
        a = parcel.readInt();
        b = parcel.readInt();
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    @Override
    public String toString() {
        return "a:" + a + " " + "b:" + b;
    }
}
