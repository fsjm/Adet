package com.fabricesalmon.adet;

import android.app.Application;

public class ExtendedApplication extends Application {
    private static ExtendedApplication m_ExtendedApplication = null;

    @Override
    public void onCreate() {
        super.onCreate();

        ExtendedApplication.m_ExtendedApplication = this;

        // Initialize the singletons so their instances
        // are bound to the application process.
        initSingletons();
    }

    protected void initSingletons() {
        // Initialize the instance of MySingleton
        ExtendedSingleton.initInstance();

        BackgroundTask_1.initInstance();
    }

    public static ExtendedApplication getExtendedApplication() {

        return m_ExtendedApplication;
    }
}
