package com.fabricesalmon.adet;

import java.util.Observer;
import java.util.Observable;
import android.util.Log;

public abstract class BackgroundTaskBridge extends Observable {

    public void NotifyObserver(Object l_Object) {
        if(BuildConfig.DEBUG) Log.i("BackgroundTaskBridge", "On NotifyObserver .....");

		setChanged();
		notifyObservers(l_Object);
    }
    public abstract void setObserver(Observer l_Observer);
    public abstract void run();

    public BackgroundTaskBridge() {
    }
}
