package com.fabricesalmon.adet;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class BackGroundHTTPRequest extends BackgroundTaskBridge {
    private final String ms_TAG = this.getClass().getSimpleName();
    private final static String CS_CHARSET = "utf-8";
    private Boolean is_Debugging = false;
    private static BackGroundHTTPRequest m_BackGroundHTTPRequest = null;

    private class ParameterObject {
        Boolean mb_isGet;
        String ms_URL;
        String ms_DataString;

        public ParameterObject(Boolean lb_isGet, String ls_URL, String ls_DataString) {
            mb_isGet = lb_isGet;
            ms_URL = ls_URL;
            ms_DataString = ls_DataString;
        }
    }
    public class DataObject {
        String ms_Response;

        public DataObject(String ls_Response) {
            ms_Response = ls_Response;
        }
    }

    public Object setParameter4Get(String ls_URL, String ls_QueryString) {

        return new ParameterObject(true, ls_URL, ls_QueryString);
    }
    public Object setParameter4Post(String ls_URL, String ls_PostData) {

        return new ParameterObject(false, ls_URL, ls_PostData);
   }

    private boolean isOnline() {
        ConnectivityManager l_ConnectivityManager =
                (ConnectivityManager) ExtendedApplication.getExtendedApplication().getSystemService(Context.CONNECTIVITY_SERVICE);

        return l_ConnectivityManager.getActiveNetworkInfo() != null &&
                l_ConnectivityManager.getActiveNetworkInfo().isConnectedOrConnecting();
    }

    private DataObject HTTPRequestSend(Boolean lb_isGet, String ls_URL, String ls_DataString) {
        URL l_URL;
        HttpURLConnection l_HttpURLConnection = null;
        DataObject l_DataObject = null;

        if (!isOnline()) return null;

        try {
// Create connection
            l_URL = new URL(ls_URL + ((lb_isGet)?("?" + ls_DataString):""));

            l_HttpURLConnection = (HttpURLConnection) l_URL.openConnection();
            l_HttpURLConnection.setRequestMethod((lb_isGet) ? "GET" : "POST");
            l_HttpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=" + CS_CHARSET);
//            l_HttpURLConnection.setRequestProperty("Content-Length","" + Integer.toString(ls_DataString.getBytes().length));
            l_HttpURLConnection.setUseCaches(false);
            l_HttpURLConnection.setDoInput(true);

// Send request
            if (!lb_isGet) {
                l_HttpURLConnection.setDoOutput(true);
                OutputStream l_OutputStream = l_HttpURLConnection.getOutputStream();
                BufferedWriter l_BufferedWriter = new BufferedWriter(
                        new OutputStreamWriter(l_OutputStream, CS_CHARSET));
                l_BufferedWriter.write(ls_DataString);
                l_BufferedWriter.flush();
                l_BufferedWriter.close();
                l_OutputStream.close();
            }
// https://github.com/mcxiaoke/android-volley/tree/master/src/main/java/com/android/volley/toolbox
// Get Response
            int li_Status = l_HttpURLConnection.getResponseCode();

            InputStream l_InputStream = null;

            if(li_Status != HttpURLConnection.HTTP_OK)
                l_InputStream = l_HttpURLConnection.getErrorStream();
            else
                l_InputStream = l_HttpURLConnection.getInputStream();

            String ls_CharsetName = l_HttpURLConnection.getContentEncoding();
            BufferedReader l_BufferedReader = null;
// According to the HTTP 1.1 spec the default charset for "text" MIME content types received via HTTP is ISO-8859-1
            if (null == ls_CharsetName) l_BufferedReader = new BufferedReader(new InputStreamReader(l_InputStream, "ISO-8859-1"));
            else l_BufferedReader = new BufferedReader(new InputStreamReader(l_InputStream, ls_CharsetName));

            String ls_Line;
            StringBuffer l_Response = new StringBuffer();
            while ((ls_Line = l_BufferedReader.readLine()) != null) {
                l_Response.append(ls_Line);
//                l_Response.append('\r');
            }
            l_BufferedReader.close();
            l_DataObject = new DataObject(l_Response.toString());

            if (is_Debugging && BuildConfig.DEBUG) Log.d(ms_TAG, "Response length: " + l_DataObject.ms_Response.length());
            if (is_Debugging && BuildConfig.DEBUG) Log.d(ms_TAG, "Response: " + l_DataObject.ms_Response);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (l_HttpURLConnection != null) l_HttpURLConnection.disconnect();
        }

        return l_DataObject;
    }

    @Override
    protected Object Processing(Object l_ParameterObject) {
        if (is_Debugging && BuildConfig.DEBUG) Log.i(ms_TAG, "Processing .....");
        ParameterObject m_ParameterObject = (ParameterObject)l_ParameterObject;

        // We notify if successfull
        DataObject m_DataObject = HTTPRequestSend(m_ParameterObject.mb_isGet, m_ParameterObject.ms_URL, m_ParameterObject.ms_DataString);

        return m_DataObject;
    }

    public static BackGroundHTTPRequest getInstance() {

        return m_BackGroundHTTPRequest;
    }
    public static String BuildParameterString(String... lsa_Argument) {
        int li_ArgumentLength = lsa_Argument.length;
        Uri.Builder l_Builder = new Uri.Builder();

        for (int i = 0; i < li_ArgumentLength; i+=2) {
            l_Builder.appendQueryParameter(lsa_Argument[i], lsa_Argument[i + 1]);
        }

        return l_Builder.build().getEncodedQuery();
    }

    public static void initInstance() {
        if (m_BackGroundHTTPRequest == null) {
            // Create the instance
            m_BackGroundHTTPRequest = new BackGroundHTTPRequest();

            if(BuildConfig.DEBUG) Log.i("BackGroundHTTPRequest", "initInstance .....");
        }
    }
    public BackGroundHTTPRequest() {
    }
}
