package com.example.StreatEatsVendor.Services;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import com.example.StreatEatsVendor.R;
import com.example.StreatEatsVendor.Services.HttpPost;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

/**
 * Created by michael on 9/08/14.
 */
public class HttpPost extends AsyncTask<HashMap, HashMap, HashMap> {

    private String url;
    Activity loginContext = null;
    private ProgressDialog progressDialog;

    public HttpPost(String url, Activity ui){
        loginContext = ui;
        this.url = url;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(loginContext);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
    }

    @Override
    protected HashMap doInBackground(HashMap... params) {
        HttpClient httpClient = new DefaultHttpClient();
        JSONObject entity = null;
        HttpResponse response;
        String responseString = "";
        try {
            entity = HttpParamsMapper.mapToParams(params[0]);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        org.apache.http.client.methods.HttpPost post = new org.apache.http.client.methods.HttpPost(url);
        post.addHeader("accept", "application/json");
        post.setHeader("Content-type", "application/json");
        try {
            StringEntity se = new StringEntity(entity.toString(), HTTP.UTF_8);
            post.setEntity(se);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            response = httpClient.execute(post);
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                responseString = EntityUtils.toString(responseEntity);
            }
            Log.i("TrucTrac", "HTTP SUCCESS - Entity: "+ responseString);
            return HttpParamsMapper.paramsToHashMap(responseString);
        } catch (IOException e) {
            Log.e("TrucTrac", "HTTP FAIL - Bad JSON has been returned: " + entity.toString());
            e.printStackTrace();
            return null;
        }
    }
    @Override
    protected void onPostExecute(HashMap result){
        progressDialog.dismiss();
        if (result.containsKey("result")){
            AlertDialog.Builder builder = new AlertDialog.Builder(loginContext);
            builder.setMessage(result.get("result").toString());
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.show();
        }
    }
}
