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

public class MainActivity extends AppCompatActivity {
    private TextView m_UserTextView = null;
    private Button m_UserButton = null;
    private EditText m_UserEditText = null;

// Add a comment !!!

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

        m_UserEditText = (EditText)findViewById(R.id.UserEditText);
    }

}
