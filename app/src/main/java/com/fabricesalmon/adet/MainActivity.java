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
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.util.Log;
import android.content.Intent;
import android.content.IntentFilter;
import java.util.Observer;
import java.util.Observable;
import android.widget.Toast;
import org.json.JSONObject;
import android.content.BroadcastReceiver;
import android.content.Context;
import java.util.List;
import java.util.ArrayList;
import android.graphics.Color;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AbsListView;

public class MainActivity extends AppCompatActivity implements Observer {
    public static final int CI_RETURN_CODE = 0;

    private final String ms_TAG = this.getClass().getSimpleName();
    Intent m_GenericBackgroundTaskIntent = null;
    private TextView m_UserTextView = null;
    private Button m_UserButton = null;
    private Button m_UserSubButton = null;

    private ListView m_UserListView = null;
    List<ItemListView> m_ItemListView = null;

    private ItemListViewAdapter m_ItemListViewAdapter = null;

    public void update(Observable l_Observable, Object l_Object) {
        if(BuildConfig.DEBUG) Log.i(ms_TAG, "update .....");

        if(l_Observable instanceof BackGroundHTTPRequest){

            setTextFromBackGroundHTTPRequest(m_UserTextView, ((BackGroundHTTPRequest.DataObject) l_Object).ms_Response);
         }
    }
    private void setTextFromBackGroundHTTPRequest(final TextView l_TextView,final String ls_String){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    Toast.makeText(ExtendedApplication.getExtendedApplication(), "Your data are ready!", Toast.LENGTH_SHORT).show();

                    JSONObject l_JSONObject = new JSONObject(ls_String);

                    JSONObject l_JSONObject4Unit = l_JSONObject.getJSONObject("unit");

                    String ls_Result = l_JSONObject.getString("location");
                    ls_Result = ls_Result.concat(": ");
                    ls_Result = ls_Result.concat(l_JSONObject.getString("chill"));
                    ls_Result = ls_Result.concat(" ");
                    ls_Result = ls_Result.concat(l_JSONObject4Unit.getString("temperature"));
                    ls_Result = ls_Result.concat(", ");
                    ls_Result = ls_Result.concat(l_JSONObject.getString("condition"));

                    l_TextView.setText(ls_Result);

                    addItemListView(ls_Result);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle l_Bundle) {
        super.onCreate(l_Bundle);

        if(BuildConfig.DEBUG) Log.i(ms_TAG, "On Create .....");

        BackGroundHTTPRequest.getInstance().addObserver(this);

        setContentView(R.layout.activity_main);

        m_GenericBackgroundTaskIntent = new Intent(this, GenericBackgroundTask.class);

        m_UserTextView = (TextView)findViewById(R.id.UserTextView);

        m_UserButton = (Button)findViewById(R.id.UserButton);
        m_UserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on lance le service
            }
        });

        m_UserSubButton = (Button)findViewById(R.id.UserSubButton);
        m_UserSubButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Réagir au clic
                Intent l_Intent = new Intent(MainActivity.this, SubActivity.class);
 //               startActivity(l_Intent);
                startActivityForResult(l_Intent, CI_RETURN_CODE);
            }
        });

        m_UserListView = (ListView) findViewById(R.id.UserListView);

        m_ItemListView = genererItemListView();
        m_UserListView.setOnScrollListener(new OnScrollListener() {
            Boolean is_ReadyToFetch = false;

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
//                Log.i(ms_TAG,"scrollState-->" +  scrollState);

                if (OnScrollListener.SCROLL_STATE_TOUCH_SCROLL == scrollState) is_ReadyToFetch = true;
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {

                Log.i(ms_TAG, firstVisibleItem + " " + visibleItemCount + " " + totalItemCount);
                if (!is_ReadyToFetch) return;

                is_ReadyToFetch = false;

                if (visibleItemCount == totalItemCount - firstVisibleItem) {

                     m_GenericBackgroundTaskIntent.putExtra(GenericBackgroundTask.CS_ACTION_TYPE, GenericBackgroundTask.CS_ACTION_GETHTTPREQUEST);

                    m_GenericBackgroundTaskIntent.putExtra(GenericBackgroundTask.CS_HTTP_REQUEST_URL, "http://weather.planetphoto.fr/weather.php");
                    m_GenericBackgroundTaskIntent.putExtra(GenericBackgroundTask.CS_HTTP_REQUEST_DATASTRING, BackGroundHTTPRequest.BuildParameterString("city", "montpellier,france", "lang", "fr"));

                    startService(m_GenericBackgroundTaskIntent);
                }
             }
        });

        m_ItemListViewAdapter = new ItemListViewAdapter(MainActivity.this, m_ItemListView);
        m_UserListView.setAdapter(m_ItemListViewAdapter);

        RestoreInstanceState(l_Bundle);
    }

    public void addItemListView(String ls_Text) {

        m_ItemListView.add(new ItemListView(Color.GRAY, ls_Text));
        m_ItemListViewAdapter.notifyDataSetChanged();
    }

    private List<ItemListView> genererItemListView(){
        List<ItemListView> l_ItemListView = new ArrayList<ItemListView>();

        l_ItemListView.add(new ItemListView(Color.BLACK, "Florent"));
        l_ItemListView.add(new ItemListView(Color.BLUE, "Kevin"));
        l_ItemListView.add(new ItemListView(Color.GREEN, "Logan"));
        l_ItemListView.add(new ItemListView(Color.RED, "Mathieu"));
        l_ItemListView.add(new ItemListView(Color.GRAY, "Willy"));

        return l_ItemListView;
    }

    protected void onActivityResult(int li_RequestCode, int li_ResultCode, Intent l_Intent) {

        // Vérification du code de retour
        if(li_RequestCode == CI_RETURN_CODE) {

            // Vérifie que le résultat est OK
            if(li_ResultCode == RESULT_OK) {
                // On récupére le paramètre "Nom" de l'intent
                String nom = l_Intent.getStringExtra("email");

                // On affiche le résultat
                Toast.makeText(this, "Your email is: " + nom, Toast.LENGTH_SHORT).show();

                // Si l'activité est annulé
            } else if (li_ResultCode == RESULT_CANCELED) {

                // On affiche que l'opération est annulée
                Toast.makeText(this, "Cancelled!", Toast.LENGTH_SHORT).show();
            }
        }
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
        l_Bundle.putString("m_UserTextView", m_UserTextView.getText().toString());

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

            if (null != ls_String) m_UserTextView.setText(ls_String);
            else m_UserTextView.setText(R.string.welcome_message);

            return;
        }

        String ls_UserTextView = l_Bundle.getString("m_UserTextView");
        m_UserTextView.setText(ls_UserTextView);
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

        ExtendedSingleton.getInstance().WriteSharedPreferences();
        super.onDestroy();
    }
}
