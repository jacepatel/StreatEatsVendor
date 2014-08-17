package com.example.StreatEatsVendor.Services;

import android.os.AsyncTask;
import android.util.Log;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by michaelsive on 4/08/2014.
 */
public class HttpParamsMapper {

    public static HashMap<String, String> paramsToHashMap(String json) {
        JSONObject jsonObject;
        HashMap<String, String> map = new HashMap<String, String>();
        try {
            Log.i("TrucTrac", "JSONValidator: json - "+json.toString());
            jsonObject = new JSONObject(json);
            Iterator keys = jsonObject.keys();
            while (keys.hasNext()) {
                String key = (String) keys.next();
                map.put(key, jsonObject.getString(key));
            }
        } catch (JSONException e) {
            Log.e("TrucTrac","Error: json malformed.");
            e.printStackTrace();
        }
        return map;
    }

    public static JSONObject mapToParams(HashMap<String, String> values) throws UnsupportedEncodingException {
        ArrayList<NameValuePair> parameters = new ArrayList<NameValuePair>();
        JSONObject json = new JSONObject();
        for (Map.Entry<String, String> entry : values.entrySet()) {
            parameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            try {
                json.put(entry.getKey(), entry.getValue());
            } catch (JSONException e) {
                Log.e("TrucTrac", "Bad HashMap: " + entry.getKey());
                e.printStackTrace();
            }
        }

        return json;
    }
}
