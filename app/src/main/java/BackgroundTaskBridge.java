package com.fabricesalmon.adet;

import android.util.Log;

import java.util.Observable;
import java.util.Observer;

public abstract class BackgroundTaskBridge extends Observable {
    private Boolean is_Debugging = false;
    private final String ms_TAG = this.getClass().getSimpleName();

    protected abstract Object Processing(Object l_ParameterObject);
    public void start(Object l_ParameterObject) {
        if (is_Debugging && BuildConfig.DEBUG) Log.i(ms_TAG, "start .....");

        if (null == l_ParameterObject) {
            Log.i(ms_TAG, "l_ParameterObject is null .....");

            return;
        }

        Object l_DataObject = Processing(l_ParameterObject);
        if (null == l_DataObject) {
            Log.i(ms_TAG, "NotifyObserver is cancelled! l_DataObject is null .....");

            return;
        }

        setChanged();
        notifyObservers(l_DataObject);
    }

    public void addObserver(Observer l_Observer) {
        if(is_Debugging && BuildConfig.DEBUG) Log.i(ms_TAG, "setObserver .....");

        super.addObserver(l_Observer);
    }

    public void deleteObserver(Observer l_Observer) {
        if(is_Debugging && BuildConfig.DEBUG) Log.i(ms_TAG, "deleteObserver .....");

        super.deleteObserver(l_Observer);
    }

    public BackgroundTaskBridge() {
    }
}
