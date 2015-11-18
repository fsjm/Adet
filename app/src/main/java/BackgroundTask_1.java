package com.fabricesalmon.adet;

import android.util.Log;
import java.util.Observer;
import android.os.SystemClock;

public class BackgroundTask_1 extends BackgroundTaskBridge {
    private final String ms_TAG = this.getClass().getSimpleName();
    private static BackgroundTask_1 m_BackgroundTask_1 = null;
    private DataObject m_DataObject = null;
    public class DataObject {
        public DataObject() {

        }
    }

    public void run() {
        if(BuildConfig.DEBUG) Log.i(ms_TAG, "run .....");

        m_DataObject = new DataObject();

        //Code
        SystemClock.sleep(2500);

        // We notify
        NotifyObserver();
    }
    public void NotifyObserver() {
        super.NotifyObserver(m_DataObject);
     }
    public void setObserver(Observer l_Observer) {
        addObserver(l_Observer);
    }
    public static BackgroundTask_1 getInstance() {

        return m_BackgroundTask_1;
    }
    public static void initInstance() {
        if (m_BackgroundTask_1 == null) {
            // Create the instance
            m_BackgroundTask_1 = new BackgroundTask_1();

            if(BuildConfig.DEBUG) Log.i("BackgroundTask_1", "initInstance .....");
        }
    }
    public BackgroundTask_1() {
    }
}
