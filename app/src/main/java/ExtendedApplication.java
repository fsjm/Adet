package com.fabricesalmon.adet;

import android.app.Application;

public class ExtendedApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize the singletons so their instances
        // are bound to the application process.
        initSingletons();
    }

    protected void initSingletons() {
        // Initialize the instance of MySingleton
        ExtendedSingleton.initInstance();
    }

    public void customAppMethod() {
        // Custom application method
    }
}
