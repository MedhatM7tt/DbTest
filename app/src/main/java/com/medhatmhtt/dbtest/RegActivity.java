package com.medhatmhtt.dbtest;

import android.content.DialogInterface;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegActivity extends AppCompatActivity {
    Button regBtn;
    EditText Email,Name,Phone,Password,ConfirmPassword;
    String name,email,phone,password,confirmPassword;
    AlertDialog.Builder builder;
    String regUrl="http://192.168.1.100/Android/Test/Reg.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        regBtn=(Button)findViewById(R.id.RegBtn);
        Email = (EditText) findViewById(R.id.RegEmail);
        Name = (EditText) findViewById(R.id.RegName);
        Password = (EditText) findViewById(R.id.RegPass);
        ConfirmPassword = (EditText) findViewById(R.id.RegConfPass);
        Phone = (EditText) findViewById(R.id.RegPhone);

        //-------------------------------------------------------------------
        builder = new AlertDialog.Builder(RegActivity.this);

        //----------------------------------------------------------------------
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name=Name.getText().toString();
                email=Email.getText().toString();
                password=Password.getText().toString();
                confirmPassword=ConfirmPassword.getText().toString();
                phone=Phone.getText().toString();

                if(name.equals("")||email.equals("")||password.equals("")||confirmPassword.equals("")){
                    builder.setTitle("Something Wrong !");
                    builder.setMessage("Fill Required !");
                    displayAlert("inputError");
                }
                else{
                    if(!password.equals(confirmPassword)){
                        builder.setTitle("Dis-matched");
                        builder.setMessage("Password not confirmed !");
                        displayAlert("inputError");
                    }
                    else{
                        StringRequest stringRequest=new StringRequest(Request.Method.POST, regUrl, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONArray jsonArray= new JSONArray(response);
                                    JSONObject jsonObject=jsonArray.getJSONObject(0);
                                    String code=jsonObject.getString("code");
                                    String message=jsonObject.getString("message");
                                    builder.setTitle("Server Response !");
                                    builder.setMessage(message);
                                    displayAlert(code);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(RegActivity.this,"Error",Toast.LENGTH_SHORT);
                                error.printStackTrace();
                            }
                        }){
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String,String> params=new HashMap<String, String>();
                                params.put("Name",name);
                                params.put("Email",email);
                                params.put("Phone",phone);
                                params.put("Password",password);
                                return params;
                            }
                        };
                        MySingleton.getmInstance(RegActivity.this).addToRequestQueue(stringRequest);
                    }
                }
            }
        });
    }

    public void displayAlert(final String message){
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(message.equals("inputError")){
                    Password.setText("");
                    ConfirmPassword.setText("");
                }
                else if(message.equals("Reg failed !")){

                    Password.setText("");
                    ConfirmPassword.setText("");
                    Email.setText("");
                    Name.setText("");
                    Phone.setText("");

                }
                else if(message.equals("Done Reg !")){
                    finish();
                }
            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }
}
