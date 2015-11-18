package com.fabricesalmon.adet;

import android.util.Log;
import java.util.Observer;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.OutputStream;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BackGroundHTTPRequest extends BackgroundTaskBridge {
    private final String ms_TAG = this.getClass().getSimpleName();
    private static BackGroundHTTPRequest m_BackGroundHTTPRequest = null;

    private ParameterObject m_ParameterObject = null;
    private DataObject m_DataObject = null;
    private Boolean mb_IsRunning = false;

    public class ParameterObject {
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

    public void setParameter4Get(String ls_URL, String ls_QueryString) {
        m_ParameterObject = new ParameterObject(true, ls_URL, ls_QueryString);
    }
    public void setParameter4Post(String ls_URL, String ls_PostData) {
        m_ParameterObject = new ParameterObject(false, ls_URL, ls_PostData);
    }

    private DataObject HTTPRequest() {

        return HTTPRequestSend(m_ParameterObject.mb_isGet, m_ParameterObject.ms_URL, m_ParameterObject.ms_DataString);

    }

    private DataObject HTTPRequestSend(Boolean lb_isGet, String ls_URL, String ls_DataString) {
        URL l_URL;
        HttpURLConnection l_HttpURLConnection = null;
        DataObject l_DataObject = null;

        try {
// Create connection
            l_URL = new URL(ls_URL + ((lb_isGet)?("?" + ls_DataString):""));

            l_HttpURLConnection = (HttpURLConnection) l_URL.openConnection();
            l_HttpURLConnection.setRequestMethod((lb_isGet) ? "GET":"POST");
            l_HttpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//            l_HttpURLConnection.setRequestProperty("Content-Length","" + Integer.toString(ls_DataString.getBytes().length));
            l_HttpURLConnection.setRequestProperty("Content-Language", "en-US");
            l_HttpURLConnection.setUseCaches(false);
            l_HttpURLConnection.setDoInput(true);

// Send request
            /*
Uri.Builder builder = new Uri.Builder()
        .appendQueryParameter("firstParam", paramValue1)
        .appendQueryParameter("secondParam", paramValue2)
        .appendQueryParameter("thirdParam", paramValue3);
String query = builder.build().getEncodedQuery();
             */
            if (!lb_isGet) {
                l_HttpURLConnection.setDoOutput(true);
                OutputStream l_OutputStream = l_HttpURLConnection.getOutputStream();
                BufferedWriter l_BufferedWriter = new BufferedWriter(
                        new OutputStreamWriter(l_OutputStream, "UTF-8"));
                l_BufferedWriter.write(ls_DataString);
                l_BufferedWriter.flush();
                l_BufferedWriter.close();
                l_OutputStream.close();
            }

// Get Response
            int li_Status = l_HttpURLConnection.getResponseCode();

            InputStream l_InputStream = null;

            if(li_Status != HttpURLConnection.HTTP_OK)
                l_InputStream = l_HttpURLConnection.getErrorStream();
            else
                l_InputStream = l_HttpURLConnection.getInputStream();

            BufferedReader l_BufferedReader = new BufferedReader(new InputStreamReader(l_InputStream));
            String ls_Line;
            StringBuffer l_Response = new StringBuffer();
            while ((ls_Line = l_BufferedReader.readLine()) != null) {
                l_Response.append(ls_Line);
                l_Response.append('\r');
            }
            l_BufferedReader.close();
            l_DataObject = new DataObject(l_Response.toString());

            if(BuildConfig.DEBUG) Log.d("Server response", l_DataObject.ms_Response);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (l_HttpURLConnection != null) l_HttpURLConnection.disconnect();
        }

        return l_DataObject;
    }

    public void run() {
        if (mb_IsRunning) {
            if(BuildConfig.DEBUG) Log.i(ms_TAG, "already runnning .....");

            return;
        }
        if (null == m_ParameterObject) {
            if(BuildConfig.DEBUG) Log.i(ms_TAG, "m_ParameterObject is null .....");

            return;
        }

        synchronized(mb_IsRunning) {
            mb_IsRunning = true;
            if(BuildConfig.DEBUG) Log.i(ms_TAG, "run .....");

            // We notify if successfull
            if (null != (m_DataObject = HTTPRequest()))
                NotifyObserver();

            // we clean parameter object
            m_ParameterObject = null;

            mb_IsRunning = false;
        }
    }
    public void NotifyObserver() {
        super.NotifyObserver(m_DataObject);
    }
    public void setObserver(Observer l_Observer) {
        addObserver(l_Observer);
    }
    public static BackGroundHTTPRequest getInstance() {

        return m_BackGroundHTTPRequest;
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
