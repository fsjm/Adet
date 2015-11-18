package com.fabricesalmon.adet;

import java.util.Observer;
import java.util.Observable;
import android.util.Log;

public abstract class BackgroundTaskBridge extends Observable implements Runnable {

    public void NotifyObserver(Object l_Object) {
		setChanged();
		notifyObservers(l_Object);
    }
    public abstract void setObserver(Observer l_Observer);

    public BackgroundTaskBridge() {
    }
}
