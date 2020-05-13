package xyz.searchandgo.app;

import android.app.Application;


public class MyApplication {
    private static MyApplication mInstance= null;

    public boolean isSuccess = false;
    public int level = 1;
    // private StringBuilder pesan = new StringBuilder();
    public String pesan;
    public String desc;
    protected MyApplication(){}

    public static synchronized MyApplication getInstance() {
        if(null == mInstance){
            mInstance = new MyApplication();
        }
        return mInstance;
    }
}

