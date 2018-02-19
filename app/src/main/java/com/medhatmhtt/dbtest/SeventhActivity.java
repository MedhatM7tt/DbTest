package com.medhatmhtt.dbtest;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class SeventhActivity extends AppCompatActivity {
    EditText ID,Name,Type;
    Button bn,bn2;
    String serverUrl="http://192.168.1.100/Android/Test/second.php";
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seventh);

        ID=(EditText) findViewById(R.id.IdEditText);
        Name=(EditText) findViewById(R.id.NameEditText);
        Type=(EditText) findViewById(R.id.TypeEditText);
        bn=(Button) findViewById(R.id.Bn13);
        bn2=(Button) findViewById(R.id.Bn14);

        builder=new AlertDialog.Builder(SeventhActivity.this);

        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name,type;
                final int id;
                name = Name.getText().toString();
                type = Type.getText().toString();
                id = Integer.parseInt(ID.getText().toString());

                StringRequest stringRequest=new StringRequest(Request.Method.POST, serverUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        builder.setTitle("Server Response");
                        builder.setMessage("Response "+response);
                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Name.setText("");
                                ID.setText("");
                                Type.setText("");
                            }
                        });
                        AlertDialog alertDialog=builder.create();
                        alertDialog.show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SeventhActivity.this,"Error",Toast.LENGTH_SHORT);
                        error.printStackTrace();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params=new HashMap<String, String>();
                        /*try {
                            params.put("Name",name);
                            params.put("Type",type);
                        }
                        catch (Exception e){
                            Toast.makeText(SeventhActivity.this,"Error Exeption"+e.toString(),Toast.LENGTH_SHORT);
                        }*/
                        params.put("Name",name);
                        params.put("Type",type);
                        params.put("ID",String.valueOf(id));
                        return params;
                    }

                };
                MySingleton.getmInstance(SeventhActivity.this).addToRequestQueue(stringRequest);
            }
        });
        bn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SeventhActivity.this,EighthActivity.class));
            }
        });
    }
}
