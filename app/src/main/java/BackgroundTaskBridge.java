package com.fabricesalmon.adet;

import java.util.Observable;
import android.util.Log;

public class BackgroundTaskBridge {
    BackgroundTask_1 m_BackgroundTask_1 = null;
    private class BackgroundTask_1 extends Observable {

        public BackgroundTask_1() {

        }
    }
    public BackgroundTaskBridge() {

        m_BackgroundTask_1 = new BackgroundTask_1();

    }
}
