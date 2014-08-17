package com.example.StreatEatsVendor;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import java.util.HashMap;
import com.example.StreatEatsVendor.Services.HttpPost;

public class LoginActivity extends Activity {
    private EditText  userName=null;
    private EditText  password=null;
    private EditText  eMail=null;
    private Button SignIn=null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        eMail= (EditText)findViewById(R.id.etEmail);
        userName = (EditText)findViewById(R.id.etUserName);
        password = (EditText)findViewById(R.id.etPassword);
        SignIn = (Button)findViewById(R.id.btnSignIn);
    }

    public void btnActLogin(View view) {
    //pass through userName & Password & Validation
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("email", eMail.getText().toString());
        params.put("password", password.getText().toString());
        String url = "http://192.168.1.102:9000/session";
        HttpPost post = new HttpPost(url, this);
        post.execute(params);
        HashMap result = null;
        try {
            result = post.get();
        }
        catch (Exception ex)
        {
            //handling for failure
        }
        if (result != null) {
            Intent intent = new Intent(this, OpenShopActivity.class);
            startActivity(intent);
            this.finish();
        }

    }

}
