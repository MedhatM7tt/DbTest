package com.medhatmhtt.dbtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EighthActivity extends AppCompatActivity {
    Button regBtnA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eighth);
        regBtnA = (Button)findViewById(R.id.Bn16);
        regBtnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EighthActivity.this,RegActivity.class));
            }
        });
    }
}
