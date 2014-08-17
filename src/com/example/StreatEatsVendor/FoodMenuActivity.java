package com.example.StreatEatsVendor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import com.example.StreatEatsVendor.Models.MenuItem;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;

/**
 * Created by jacep_000 on 13-08-14.
 */
public class FoodMenuActivity extends Activity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_FoodMenu);
        //userName = (EditText)findViewById(R.id.etUserName);
        //password = (EditText)findViewById(R.id.etPassword);
        //SignIn = (Button)findViewById(R.id.btnSignIn);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        // Handle item selection
        Intent intent = new Intent();
        switch (item.getItemId()) {
            case R.id.action_openshop:
                //Intent to open new shop
                intent = new Intent(this, OpenShopActivity.class);
                startActivity(intent);
                this.finish();
                return true;
            case R.id.action_menu:
                //Intent to show food menu
                intent = new Intent(this, FoodMenuActivity.class);
                startActivity(intent);
                this.finish();
            case R.id.action_settings:
                //Intent to open settings
                intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                this.finish();
            default:
                //Something ABout menu item missing or some shit
                return true;
        }
    }


}
