package com.fabricesalmon.adet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

public class SubActivity extends AppCompatActivity {
    private final String ms_TAG = this.getClass().getSimpleName();
    private EditText m_UserEmailEditText = null;

    @Override
    protected void onCreate(Bundle l_Bundle) {
        super.onCreate(l_Bundle);

        if(BuildConfig.DEBUG) Log.i(ms_TAG, "On Create .....");

        setContentView(R.layout.activity_sub);

        m_UserEmailEditText = (EditText)findViewById(R.id.UserEmailEditText);

        RestoreInstanceState(l_Bundle);
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

        if(BuildConfig.DEBUG) Log.i(ms_TAG, "On SaveInstanceState .....");

        // Save UI state changes to the savedInstanceState.
        // This bundle will be passed to onCreate if the process is
        // killed and restarted.
        l_Bundle.putString("m_UserEmailEditText", m_UserEmailEditText.getText().toString());
    }

    @Override
    public void onRestoreInstanceState(Bundle l_Bundle) {
        super.onRestoreInstanceState(l_Bundle);

        if(BuildConfig.DEBUG) Log.i(ms_TAG, "On RestoreInstanceState .....");

        // Restore UI state from the savedInstanceState.
        // This bundle has also been passed to onCreate.

        RestoreInstanceState(l_Bundle);
    }

    private void RestoreInstanceState(Bundle l_Bundle) {

        if (null == l_Bundle) {
            String ls_String = (String)ExtendedSingleton.getValueFromSharedPreferences("email");

            if (null != ls_String) m_UserEmailEditText.setText(ls_String);

            return;
        }

        String ls_UserEmailEditText = l_Bundle.getString("m_UserEmailEditText");
        m_UserEmailEditText.setText(ls_UserEmailEditText);
    }

    @Override
    protected void onPause() {
        if(BuildConfig.DEBUG) Log.i(ms_TAG, "On Pause .....");

        super.onPause();
    }

    @Override
    protected void onStop() {
        if(BuildConfig.DEBUG) Log.i(ms_TAG, "On Stop .....");

        super.onStop();
    }

    @Override
    protected void onDestroy() {
        if(BuildConfig.DEBUG) Log.i(ms_TAG, "On Destroy .....");

        super.onDestroy();
    }

    @Override
    public void onBackPressed() {

        if(BuildConfig.DEBUG) Log.i(ms_TAG, "On BackPressed .....");

        String ls_UserEmailEditText = m_UserEmailEditText.getText().toString();
        ExtendedSingleton.setValueToSharedPreferences("email", ls_UserEmailEditText);

        // Otherwise defer to system default behavior.
        super.onBackPressed();
    }
}
