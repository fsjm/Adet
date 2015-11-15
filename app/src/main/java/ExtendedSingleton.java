package com.fabricesalmon.adet;

import android.util.Log;

public class ExtendedSingleton {
    private static ExtendedSingleton l_ExtendedSingleton = null;

    public static void initInstance() {
        if (l_ExtendedSingleton == null) {
            // Create the instance
            l_ExtendedSingleton = new ExtendedSingleton();

            if(BuildConfig.DEBUG) Log.i("ExtendedSingleton", "initInstance .....");
        }
    }

    public static ExtendedSingleton getInstance() {
        return l_ExtendedSingleton;
    }

    private ExtendedSingleton() {
    }
}
