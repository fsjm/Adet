package com.fabricesalmon.adet;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.util.Log;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {
    private final String ms_TAG = this.getClass().getSimpleName();
    private TextView m_UserTextView = null;
    private Button m_UserButton = null;
    private Button m_UserSubButton = null;
    private EditText m_UserEditText = null;

// Add a comment !!!!

    @Override
    protected void onCreate(Bundle l_Bundle) {
        super.onCreate(l_Bundle);

        setContentView(R.layout.activity_main);

        m_UserTextView = (TextView)findViewById(R.id.UserTextView);
        m_UserTextView.setText(R.string.welcome_message);

        m_UserButton = (Button)findViewById(R.id.UserButton);
        m_UserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Réagir au clic
                m_UserTextView.setText(m_UserEditText.getText());
            }
        });

        m_UserSubButton = (Button)findViewById(R.id.UserSubButton);
        m_UserSubButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Réagir au clic
                Intent l_Intent = new Intent(MainActivity.this, SubActivity.class);
                startActivity(l_Intent);
             }
        });

        m_UserEditText = (EditText)findViewById(R.id.UserEditText);

        RestoreInstanceState(l_Bundle);

        if(BuildConfig.DEBUG) Log.i(ms_TAG, "On Create .....");
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
        l_Bundle.putString("m_UserEditText", m_UserEditText.getText().toString());

        if(BuildConfig.DEBUG) Log.i(ms_TAG, "On SaveInstanceState .....");
    }

    @Override
    public void onRestoreInstanceState(Bundle l_Bundle) {
        super.onRestoreInstanceState(l_Bundle);

        // Restore UI state from the savedInstanceState.
        // This bundle has also been passed to onCreate.
        RestoreInstanceState(l_Bundle);

        if(BuildConfig.DEBUG) Log.i(ms_TAG, "On RestoreInstanceState .....");
    }

    private void RestoreInstanceState(Bundle l_Bundle) {

        if (null == l_Bundle) {
            String ls_String = (String)ExtendedSingleton.getValueFromSharedPreferences("email");

            if (null != ls_String) m_UserEditText.setText(ls_String);

            return;
        }

        String ls_UserEditText = l_Bundle.getString("m_UserEditText");
        m_UserEditText.setText(ls_UserEditText);
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

        ExtendedSingleton.getInstance().WriteSharedPreferences();
        super.onDestroy();
    }
}
