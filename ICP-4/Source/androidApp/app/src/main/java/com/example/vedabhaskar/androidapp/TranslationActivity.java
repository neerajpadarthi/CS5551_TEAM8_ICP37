package com.example.vedabhaskar.androidapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Button;
import org.json.JSONArray;
import org.json.JSONException;
import android.widget.Spinner;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TranslationActivity extends AppCompatActivity {

    String sourceText;
    TextView outputTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translation);
        addItemsOnSpinner();
        outputTextView = (TextView) findViewById(R.id.resViewTxt);

        Button signOutCntrl = (Button)findViewById(R.id.signOutBtn);
        signOutCntrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent redirect = new Intent(TranslationActivity.this, LoginActivity.class);
                startActivity(redirect);
            }
        });
    }

    public HashMap<String, String> dataValues() {
        HashMap<String, String> hmap = new HashMap<String, String>();
        hmap.put("Arabic", "ar");
        hmap.put("Abkhazian", "ab");
        hmap.put("Bambara", "bm");
        hmap.put("Basque", "eu");
        hmap.put("Bengali", "bn");
        hmap.put("Chinese", "zh");
        hmap.put("Danish", "da");
        hmap.put("France", "es");
        hmap.put("German", "de");
        hmap.put("Indonesian", "id");
        hmap.put("Irish", "ga");
        hmap.put("Italian", "it");
        hmap.put("Kashmiri", "ks");
        hmap.put("Lao", "lo");
        hmap.put("Zulu", "zu");
        return hmap;
    }

    public void addItemsOnSpinner() {
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        HashMap<String, String> hmap = dataValues();
        List<String> list = new ArrayList<String>();
        for (Map.Entry m : hmap.entrySet()) {
            list.add(m.getKey().toString());
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(dataAdapter);
    }

    public void translateText(View v) {
        TextView sourceTextView = (TextView) findViewById(R.id.resultText);
        LinearLayout linearLayout2Cont = (LinearLayout)findViewById(R.id.linearLayout2);
        linearLayout2Cont.setVisibility(View.VISIBLE);
        String toLang=null;
        Spinner spinner1 = (Spinner) findViewById(R.id.spinner2);
        HashMap<String, String> hmap = dataValues();
        for (Map.Entry m : hmap.entrySet()) {
            if (m.getKey().equals(spinner1.getSelectedItem().toString())) {
                toLang=m.getValue().toString();
            }
        }
        sourceText = sourceTextView.getText().toString();
        String getURL = "https://translate.yandex.net/api/v1.5/tr.json/translate?" +
                "key=trnsl.1.1.20151023T145251Z.bf1ca7097253ff7e." +
                "c0b0a88bea31ba51f72504cc0cc42cf891ed90d2&text=" + sourceText +"&" +
                "lang=en-"+ toLang +"&[format=plain]&[options=1]&[callback=set]";//The API service URL

        OkHttpClient client = new OkHttpClient();
        try {
            Request request = new Request.Builder()
                    .url(getURL)
                    .build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    System.out.println(e.getMessage());
                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    final JSONObject jsonResult;
                    final String result = response.body().string();
                    try {
                        jsonResult = new JSONObject(result);
                        JSONArray convertedTextArray = jsonResult.getJSONArray("text");
                        final String convertedText = convertedTextArray.get(0).toString();
                        Log.d("okHttp", jsonResult.toString());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                outputTextView.setText(convertedText);
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception ex) {
            outputTextView.setText(ex.getMessage());
        }
    }
}
