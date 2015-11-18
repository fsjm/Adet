package com.fabricesalmon.adet;

import android.util.Log;
import java.util.Observer;
import android.os.SystemClock;

public class BackgroundTask_1 extends BackgroundTaskBridge {
    private final String ms_TAG = this.getClass().getSimpleName();
    private static BackgroundTask_1 m_BackgroundTask_1 = null;

    private ParameterObject m_ParameterObject = null;
    private DataObject m_DataObject = null;
    private Boolean mb_IsRunning = false;

    public class ParameterObject {
        public ParameterObject() {

        }
    }
    public class DataObject {
        public DataObject() {

        }
    }

    public void setParameterObject() {
        m_ParameterObject = new ParameterObject();
    }

    public void run() {
        if (mb_IsRunning) {
            if(BuildConfig.DEBUG) Log.i(ms_TAG, "already runnning .....");

            return;
        }
        if (null == m_ParameterObject) {
            if(BuildConfig.DEBUG) Log.i(ms_TAG, "m_ParameterObject is null .....");

            return;
        }

        synchronized(mb_IsRunning) {
            mb_IsRunning = true;
            if(BuildConfig.DEBUG) Log.i(ms_TAG, "run .....");

            m_DataObject = new DataObject();

            //Code using m_ParameterObject
            SystemClock.sleep(2500);

            // We notify
            NotifyObserver();

            // we clean parameter object
            m_ParameterObject = null;

            mb_IsRunning = false;
        }
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
