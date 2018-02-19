package com.medhatmhtt.dbtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

public class ThirdActivity extends AppCompatActivity {
    String serverUrl="http://192.168.1.100/Android/Test/first.php";
    Button bn,bn2;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        bn = ( Button ) findViewById(R.id.Bn5);
        bn2 = ( Button ) findViewById(R.id.Bn6);
        tv= ( TextView ) findViewById(R.id.serverView3);
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest stringRequest=new StringRequest(Request.Method.POST, serverUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        tv.setText(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        tv.setText("Erorrrrr");
                        error.printStackTrace();
                    }
                });
                MySingleton.getmInstance(getApplicationContext()).addToRequestQueue(stringRequest);
            }
        });
        bn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ThirdActivity.this,FourthActivity.class));
            }
        });
    }
}
