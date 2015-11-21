package com.fabricesalmon.adet;

import android.util.Log;
import java.util.Observer;
import android.os.SystemClock;

public class BackgroundTask_1 extends BackgroundTaskBridge {
    private final String ms_TAG = this.getClass().getSimpleName();
    private static BackgroundTask_1 m_BackgroundTask_1 = null;

    public class ParameterObject {
        public ParameterObject() {

        }
    }
    public class DataObject {
        public DataObject() {

        }
    }

    public Object setParameterObject() {
        return new ParameterObject();
    }

    @Override
    protected Object Processing(Object l_ParameterObject) {
        if(BuildConfig.DEBUG) Log.i(ms_TAG, "Processing .....");

        //Code using m_ParameterObject
        DataObject l_DataObject = null; // Result of internal processing

        return l_DataObject;
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
