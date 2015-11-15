package com.fabricesalmon.adet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class SubActivity extends AppCompatActivity {
    private final String ms_TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(BuildConfig.DEBUG) Log.i(ms_TAG, "On Start .....");
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        if(BuildConfig.DEBUG) Log.i(ms_TAG, "On Restart .....");
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(BuildConfig.DEBUG) Log.i(ms_TAG, "On Resume .....");
    }

    @Override
    public void onSaveInstanceState(Bundle l_Bundle) {
        super.onSaveInstanceState(l_Bundle);

        // Save UI state changes to the savedInstanceState.
        // This bundle will be passed to onCreate if the process is
        // killed and restarted.

        if(BuildConfig.DEBUG) Log.i(ms_TAG, "On SaveInstanceState .....");
    }

    @Override
    public void onRestoreInstanceState(Bundle l_Bundle) {
        super.onRestoreInstanceState(l_Bundle);

        // Restore UI state from the savedInstanceState.
        // This bundle has also been passed to onCreate.

        if(BuildConfig.DEBUG) Log.i(ms_TAG, "On RestoreInstanceState .....");
    }

    private void RestoreInstanceState(Bundle l_Bundle) {

        if (null == l_Bundle) return;
    }

    @Override
    protected void onPause() {
        super.onPause();

        if(BuildConfig.DEBUG) Log.i(ms_TAG, "On Pause .....");
    }

    @Override
    protected void onStop() {
        super.onStop();

        if(BuildConfig.DEBUG) Log.i(ms_TAG, "On Stop .....");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(BuildConfig.DEBUG) Log.i(ms_TAG, "On Destroy .....");
    }

    @Override
    public void onBackPressed() {

        if(BuildConfig.DEBUG) Log.i(ms_TAG, "On BackPressed .....");

        // Otherwise defer to system default behavior.
        super.onBackPressed();
    }
}
