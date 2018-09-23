package com.example.vedabhaskar.mapsproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class MainActivity extends AppCompatActivity {

    private static final String TAG="MainActivity";
    private static final int ERROR_DIALOG_REQUEST=9001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (checkUser())
        {
            System.out.println("no issue");

            Button trackButton = (Button)findViewById(R.id.mapButton);
                    trackButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            System.out.println("button clicked");

                            Intent redirect = new Intent(MainActivity.this, mapActivity.class);
                            startActivity(redirect);
                        }
                    });



        }

    }

    public boolean checkUser()
    {
        System.out.println("Check user");
        int tst= GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);

        if (tst== ConnectionResult.SUCCESS)
        {
            System.out.println("Working");
            return true;
        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(tst))
        {
            System.out.println("Can be resolved");
        }
        else
        {
            Toast.makeText(this, "cannot map req", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

}
