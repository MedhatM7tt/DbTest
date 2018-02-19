package com.medhatmhtt.dbtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class SixthActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<MovieAward> arrayList=new ArrayList<>();
    Button bn,bn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sixth);
        bn=(Button)findViewById(R.id.Bn11);
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SixthActivity.this,SeventhActivity.class));
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.RecycleDb);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(SixthActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        BackgroundTask backgroundTask=new BackgroundTask(SixthActivity.this);
        arrayList = backgroundTask.getArrayList();
        adapter=new RecyclerAdapter(arrayList);
        recyclerView.setAdapter(adapter);


    }
}
