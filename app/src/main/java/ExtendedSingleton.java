package com.fabricesalmon.adet;

import android.util.Log;

public class ExtendedSingleton {
    private static ExtendedSingleton m_ExtendedSingleton = null;

    private static ExtendedSharedPreferences m_ExtendedSharedPreferences;

    public static void initInstance() {
        if (m_ExtendedSingleton == null) {
            // Create the instance
            m_ExtendedSingleton = new ExtendedSingleton();

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
        return m_ExtendedSingleton;
    }

    private ExtendedSingleton() {

        m_ExtendedSharedPreferences = new ExtendedSharedPreferences("Global");
    }
}
