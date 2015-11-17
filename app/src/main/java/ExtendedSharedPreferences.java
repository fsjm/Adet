package com.fabricesalmon.adet;

import java.util.HashMap;
import java.util.Map;
import android.content.SharedPreferences;
import android.util.Log;

public class ExtendedSharedPreferences {
    private final String ms_TAG = this.getClass().getSimpleName();
    String ms_Name = null;
    HashMap m_ExtendedSharedPreferences = null;
    Boolean mb_ToBeWritten = false;

    public Object getValue(String ls_Key) {

        if (m_ExtendedSharedPreferences.containsKey(ls_Key)) return m_ExtendedSharedPreferences.get(ls_Key);

        return null;
    }

    public void setValue(String ls_Key, Object l_Object) {

        mb_ToBeWritten = true;

        m_ExtendedSharedPreferences.put(ls_Key, l_Object);
    }

    public void Write() {

        if (!mb_ToBeWritten) return;

        SharedPreferences l_SharedPreferences = ExtendedApplication.getExtendedApplication().getSharedPreferences(ms_Name, 0);
        SharedPreferences.Editor l_Editor = l_SharedPreferences.edit();

        for (Object l_Key : m_ExtendedSharedPreferences.keySet() ) {
            Object l_object = m_ExtendedSharedPreferences.get((String)l_Key);

            if (l_object instanceof Boolean) l_Editor.putBoolean((String) l_Key, (Boolean) l_object);
            if (l_object instanceof Integer) l_Editor.putInt((String) l_Key, (int) l_object);
            if (l_object instanceof String) l_Editor.putString((String)l_Key, (String)l_object);
        }

        l_Editor.commit();

        if(BuildConfig.DEBUG) Log.i(ms_TAG, "Write .....");
    }

    public ExtendedSharedPreferences(String ls_Name) {

        ms_Name = ls_Name;

        m_ExtendedSharedPreferences = new HashMap();

        SharedPreferences l_SharedPreferences = ExtendedApplication.getExtendedApplication().getSharedPreferences(ms_Name, 0);
        Map<String, ?> l_Keys = l_SharedPreferences.getAll();

        if (null != l_Keys)
            for (Map.Entry<String, ?> l_Entry : l_Keys.entrySet()) m_ExtendedSharedPreferences.put(l_Entry.getKey(), l_Entry.getValue());
    }
 }
