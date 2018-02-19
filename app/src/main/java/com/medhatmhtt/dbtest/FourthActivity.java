package com.medhatmhtt.dbtest;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;

public class FourthActivity extends AppCompatActivity {

    String serverUrl="http://192.168.1.100/Android/Test/img.jpg";
    Button bn,bn2;
    ImageView Iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        bn = (Button) findViewById(R.id.Bn7);
        bn2 = ( Button ) findViewById(R.id.Bn8);
        Iv= (ImageView) findViewById(R.id.serverView4);
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageRequest imageRequest = new ImageRequest(serverUrl, new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        Iv.setImageBitmap(response);
                    }
                },0,0, ImageView.ScaleType.CENTER_CROP,null,new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(FourthActivity.this,"SomeThing went Wrong !",Toast.LENGTH_SHORT);
                    }
                });
                MySingleton.getmInstance(FourthActivity.this).addToRequestQueue(imageRequest);
            }
        });
        bn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FourthActivity.this,FifthActivity.class));
            }
        });
    }
}
