package com.fabricesalmon.adet;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class GenericBackgroundTask extends IntentService  {
    private Boolean is_Debugging = false;
    public static final String CS_ACTION_TYPE = "ActionType";

    public static final String CS_ACTION_FORTESTING = "ForTesting";

    public static final String CS_ACTION_GETHTTPREQUEST = "GetHttpRequest";
    public static final String CS_ACTION_POSTHTTPREQUEST = "PostHttpRequest";
    public static final String CS_HTTP_REQUEST_URL = "HttpRequest_URL";
    public static final String CS_HTTP_REQUEST_DATASTRING = "HttpRequest_DataString";

    private final String ms_TAG = this.getClass().getSimpleName();

    public GenericBackgroundTask() {
        super("GenericBackgroundTask");

        if (is_Debugging && BuildConfig.DEBUG) Log.i(ms_TAG, "GenericBackgroundTask .....");
    }

    @Override
    protected void onHandleIntent(Intent l_Intent) {

        if (is_Debugging && BuildConfig.DEBUG) Log.i(ms_TAG, "onHandleIntent .....");

        String ls_ActionType = l_Intent.getStringExtra(CS_ACTION_TYPE);
        if (is_Debugging && BuildConfig.DEBUG) Log.i(ms_TAG, "CS_ACTION_TYPE: " + ls_ActionType);

          switch (ls_ActionType) {
              case CS_ACTION_FORTESTING:

                break;
              case CS_ACTION_GETHTTPREQUEST:
              case CS_ACTION_POSTHTTPREQUEST:
                  String ls_URL = l_Intent.getStringExtra(CS_HTTP_REQUEST_URL);
                  String ls_DataString = l_Intent.getStringExtra(CS_HTTP_REQUEST_DATASTRING);

                  Object l_ParameterObject = null;
                  BackGroundHTTPRequest l_BackGroundHTTPRequest = BackGroundHTTPRequest.getInstance();

                  if (CS_ACTION_GETHTTPREQUEST == ls_ActionType) l_ParameterObject = l_BackGroundHTTPRequest.setParameter4Get(ls_URL, ls_DataString);
                  else l_ParameterObject = l_BackGroundHTTPRequest.setParameter4Post(ls_URL, ls_DataString);

                  l_BackGroundHTTPRequest.start(l_ParameterObject);

                 break;
               default:

                  if (is_Debugging && BuildConfig.DEBUG) Log.i(ms_TAG, "Unknown action type: " + ls_ActionType);
                  break;
        }
     }
}
