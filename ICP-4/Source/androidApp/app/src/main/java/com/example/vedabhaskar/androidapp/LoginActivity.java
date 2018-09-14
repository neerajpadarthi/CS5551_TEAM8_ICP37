package com.example.vedabhaskar.androidapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button subBtnCntrl = (Button)findViewById(R.id.subBtn);
        subBtnCntrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText userNmaeCntrl = (EditText) findViewById(R.id.userNameTxt);
                EditText passwordCntrl = (EditText) findViewById(R.id.passwordTxt);
                TextView errorText = (TextView)findViewById(R.id.errorText);
                HashMap<String, String> hmap = new HashMap<String, String>();
                hmap.put("NMPKXR", "nmpkxr");
                hmap.put("Admin", "Admin");
                hmap.put("User", "User");
                boolean validationFlag = false;
                if (!userNmaeCntrl.getText().toString().isEmpty() && !passwordCntrl.getText().toString().isEmpty()) {
                    for (Map.Entry m : hmap.entrySet()) {
                        if (m.getKey().equals(userNmaeCntrl.getText().toString())) {
                            if (m.getValue().equals(passwordCntrl.getText().toString())) {
                                validationFlag = true;
                                break;
                            }
                        }
                    }
                }
                if(!validationFlag)
                {
                    errorText.setVisibility(View.VISIBLE);
                }
                else
                {
                    Intent redirect = new Intent(LoginActivity.this, TranslationActivity.class);
                    startActivity(redirect);
                }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}