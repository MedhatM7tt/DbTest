package com.medhatmhtt.dbtest;

import android.app.VoiceInteractor;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {
    private Button bn;
    private Button bn2;
    private TextView tv;
    private String server_url="http://192.168.1.100/Android/Test/first.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bn=(Button) findViewById(R.id.Bn);
        bn2=(Button) findViewById(R.id.Bn2);
        tv=(TextView)findViewById(R.id.serverView);

        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final RequestQueue requestQueue= Volley.newRequestQueue(MainActivity.this);
                StringRequest stringRequest=new StringRequest(Request.Method.POST, server_url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        tv.setText(response);
                        requestQueue.stop();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        tv.setText("Error");
                        error.printStackTrace();
                        requestQueue.stop();
                    }
                });
                requestQueue.add(stringRequest);
            }
        });

        bn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,SecondActivity.class);
                startActivity(i);
            }
        });

    }
}
