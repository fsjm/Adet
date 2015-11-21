package com.fabricesalmon.adet;

import java.util.Observer;
import java.util.Observable;
import android.util.Log;

public abstract class BackgroundTaskBridge extends Observable {
    private final String ms_TAG = this.getClass().getSimpleName();

    protected abstract Object Processing(Object l_ParameterObject);
    public void start(Object l_ParameterObject) {
        if(BuildConfig.DEBUG) Log.i(ms_TAG, "start .....");

        if (null == l_ParameterObject) {
            Log.i(ms_TAG, "l_ParameterObject is null .....");

            return;
        }

        Object l_DataObject = Processing(l_ParameterObject);
        if (null == l_DataObject) {
            Log.i(ms_TAG, "NotifyObserver is cancelled! l_DataObject is null .....");

            return;
        }

        NotifyObserver(l_DataObject);
    }

    public void NotifyObserver(Object l_Object) {
        if(BuildConfig.DEBUG) Log.i("BackgroundTaskBridge", "NotifyObserver .....");

		setChanged();
		notifyObservers(l_Object);
    }
    public void setObserver(Observer l_Observer) {
        if(BuildConfig.DEBUG) Log.i(ms_TAG, "setObserver .....");

        addObserver(l_Observer);
    }

    public BackgroundTaskBridge() {
    }
}
