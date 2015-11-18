package com.fabricesalmon.adet;

import android.util.Log;

public class ExtendedSingleton {
    private static ExtendedSingleton l_ExtendedSingleton = null;

    private static ExtendedSharedPreferences m_ExtendedSharedPreferences;
    private static BackgroundTaskBridge m_BackgroundTaskBridge;

    public static void initInstance() {
        if (l_ExtendedSingleton == null) {
            // Create the instance
            l_ExtendedSingleton = new ExtendedSingleton();

            if(BuildConfig.DEBUG) Log.i("ExtendedSingleton", "initInstance .....");
        }
    }

    public static Object getValueFromSharedPreferences(String ls_Key) {

        return m_ExtendedSharedPreferences.getValue(ls_Key);

    }

    public static void setValueToSharedPreferences(String ls_Key, int li_Value) {

        m_ExtendedSharedPreferences.setValue(ls_Key, (Object)li_Value);
    }
    public static void setValueToSharedPreferences(String ls_Key, Boolean lb_Value) {

        m_ExtendedSharedPreferences.setValue(ls_Key, (Object)lb_Value);
    }
    public static void setValueToSharedPreferences(String ls_Key, String ls_Value) {

        m_ExtendedSharedPreferences.setValue(ls_Key, (Object)ls_Value);
    }
    public void WriteSharedPreferences() {

        m_ExtendedSharedPreferences.Write();
    }

    public static ExtendedSingleton getInstance() {
        return l_ExtendedSingleton;
    }

    private ExtendedSingleton() {

        m_ExtendedSharedPreferences = new ExtendedSharedPreferences("Global");
        m_BackgroundTaskBridge = new BackgroundTaskBridge();
    }
}
