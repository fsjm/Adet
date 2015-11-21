package com.fabricesalmon.adet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.Toast;

import java.util.Observable;
import java.util.Observer;

public class SubActivity extends AppCompatActivity implements Observer {
    private final String ms_TAG = this.getClass().getSimpleName();
    Intent m_GenericBackgroundTaskIntent = null;

    private EditText m_UserEmailEditText = null;
    private Button m_Sub_LauchHTTPRequest = null;
    private Button m_Sub_UserSubmit = null;
    private Button m_Sub_UserCancel = null;

    public void update(Observable l_Observable, Object l_Object) {
        if(BuildConfig.DEBUG) Log.i(ms_TAG, "update .....");

    }

    @Override
    protected void onCreate(Bundle l_Bundle) {
        super.onCreate(l_Bundle);

        if(BuildConfig.DEBUG) Log.i(ms_TAG, "On Create .....");

        BackGroundHTTPRequest.getInstance().addObserver(this);

        setContentView(R.layout.activity_sub);

        m_GenericBackgroundTaskIntent = new Intent(this, GenericBackgroundTask.class);

        m_UserEmailEditText = (EditText)findViewById(R.id.UserEmailEditText);

        m_Sub_LauchHTTPRequest = (Button)findViewById(R.id.Sub_LauchHTTPRequest);
        m_Sub_LauchHTTPRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                m_GenericBackgroundTaskIntent.putExtra(GenericBackgroundTask.CS_ACTION_TYPE, GenericBackgroundTask.CS_ACTION_GETHTTPREQUEST);

                m_GenericBackgroundTaskIntent.putExtra(GenericBackgroundTask.CS_HTTP_REQUEST_URL, "http://weather.planetphoto.fr/weather.php");
                m_GenericBackgroundTaskIntent.putExtra(GenericBackgroundTask.CS_HTTP_REQUEST_DATASTRING, BackGroundHTTPRequest.BuildParameterString("city", "montpellier,france", "lang", "fr"));

                startService(m_GenericBackgroundTaskIntent);
            }
        });
        m_Sub_UserSubmit = (Button)findViewById(R.id.Sub_UserSubmit);
        m_Sub_UserSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ls_UserEmailEditText = m_UserEmailEditText.getText().toString();

                if (0 >= ls_UserEmailEditText.length()) {
                    Toast.makeText(ExtendedApplication.getExtendedApplication(), "Cannot be empty!", Toast.LENGTH_SHORT).show();

                    return;
                }

                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(ls_UserEmailEditText).matches()){
                    Toast.makeText(ExtendedApplication.getExtendedApplication(), "Wrong email format!", Toast.LENGTH_SHORT).show();

                    return;
                }

                ExtendedSingleton.setValueToSharedPreferences("email", ls_UserEmailEditText);

                // Création de l'intent
                Intent l_Intent = new Intent();

                // On rajoute l'email saisi dans l'intent
                l_Intent.putExtra("email", ls_UserEmailEditText);

                // On retourne le résultat avec l'intent
                setResult(RESULT_OK, l_Intent);

                finish();
            }
        });
        m_Sub_UserCancel = (Button) findViewById(R.id.Sub_UserCancel);
        m_Sub_UserCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Création de l'intent
                Intent l_Intent = new Intent();

                // On retourne le résultat avec l'intent
                setResult(RESULT_CANCELED, l_Intent);

                finish();
            }
        });
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

        BackGroundHTTPRequest.getInstance().deleteObserver(this);

        super.onDestroy();
    }

    @Override
    public void onBackPressed() {

        if(BuildConfig.DEBUG) Log.i(ms_TAG, "On BackPressed .....");

        // Otherwise defer to system default behavior.
        super.onBackPressed();
    }
}
