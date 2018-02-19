package com.medhatmhtt.dbtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;

public class SecondActivity extends AppCompatActivity {
    Button bn,bn2;
    TextView tv;
    RequestQueue requestQueue;
    String serverUrl="http://192.168.1.100/Android/Test/first.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        bn=(Button)findViewById(R.id.Bn3);
        bn2=(Button)findViewById(R.id.Bn4);
        tv=(TextView) findViewById(R.id.serverView2);

        Cache cache =new DiskBasedCache(getCacheDir(),1024*1024);
        Network network=new BasicNetwork(new HurlStack());
        requestQueue=new RequestQueue(cache,network);
        requestQueue.start();

        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest stringRequest=new StringRequest(Request.Method.POST, serverUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        tv.setText(response);
                        requestQueue.stop();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        tv.setText("Erorrrrr");
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
                Intent i=new Intent (SecondActivity.this,ThirdActivity.class);
                startActivity(i);
            }
        });
    }
}
