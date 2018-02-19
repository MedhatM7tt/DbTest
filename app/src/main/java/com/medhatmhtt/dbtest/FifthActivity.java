package com.medhatmhtt.dbtest;

import android.app.VoiceInteractor;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class FifthActivity extends AppCompatActivity {
    Button bn,bn2;
    TextView tv;
    String serverUrl="http://192.168.1.100/Android/Test/first.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth);
        bn=(Button) findViewById(R.id.Bn9);
        bn2=(Button) findViewById(R.id.Bn10);
        tv=(TextView)findViewById(R.id.serverView5);

        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, serverUrl, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            tv.setText(response.getString("ID"));
                        } catch (JSONException e) {
                            Toast.makeText(FifthActivity.this,"Something went wrong",Toast.LENGTH_SHORT);
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(FifthActivity.this,"Something went wrong",Toast.LENGTH_SHORT);
                        error.printStackTrace();
                    }
                });
                MySingleton.getmInstance(FifthActivity.this).addToRequestQueue(jsonObjectRequest);
            }
        });
        bn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FifthActivity.this,SixthActivity.class));
            }
        });
    }
}
