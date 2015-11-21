package com.fabricesalmon.adet;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class GenericBackgroundTask extends IntentService  {
    public static final String CS_ACTION_TYPE = "ActionType";

    public static final String CS_ACTION_FORTESTING = "ForTesting";

    public static final String CS_ACTION_HTTPREQUEST = "HttpRequest";
    public static final String CS_HTTP_REQUEST_URL = "HttpRequest_URL";
    public static final String CS_HTTP_REQUEST_DATASTRING = "HttpRequest_DataString";

    private final String ms_TAG = this.getClass().getSimpleName();

    public GenericBackgroundTask() {
        super("GenericBackgroundTask");

        if(BuildConfig.DEBUG) Log.i(ms_TAG, "GenericBackgroundTask .....");
    }

    @Override
    protected void onHandleIntent(Intent l_Intent) {

        if (BuildConfig.DEBUG) Log.i(ms_TAG, "onHandleIntent .....");

        String ls_ActionType = l_Intent.getStringExtra(CS_ACTION_TYPE);
        if (BuildConfig.DEBUG) Log.i(ms_TAG, "CS_ACTION_TYPE: " + ls_ActionType);

          switch (ls_ActionType) {
              case CS_ACTION_FORTESTING:

                break;
              case CS_ACTION_HTTPREQUEST:
                  String ls_URL = l_Intent.getStringExtra(CS_HTTP_REQUEST_URL);
                  String ls_DataString = l_Intent.getStringExtra(CS_HTTP_REQUEST_DATASTRING);

                  BackGroundHTTPRequest.getInstance().setParameter4Get(ls_URL, ls_DataString);
                  BackGroundHTTPRequest.getInstance().run();

                 break;
              default:

                  if (BuildConfig.DEBUG) Log.i(ms_TAG, "Unknown action type: " + ls_ActionType);
                  break;
        }
     }
}
