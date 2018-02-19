package com.medhatmhtt.dbtest;

import android.app.VoiceInteractor;
import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Created by SM on 2/18/2018.
 */

public class BackgroundTask {
    Context ctx;
    ArrayList<MovieAward> arrayList=new ArrayList<>();
    String serverUrl="http://192.168.1.100/Android/Test/first.php";

    public BackgroundTask(Context ctx) {
        this.ctx = ctx;
    }

    public ArrayList<MovieAward> getArrayList(){
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, serverUrl, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                int count=0;
                while(count<response.length())
                {
                    JSONObject jsonObject= null;
                    try {
                        jsonObject = response.getJSONObject(count);
                        MovieAward movieAward = new MovieAward(jsonObject.getInt("ID"),jsonObject.getString("Name"),jsonObject.getString("Type"));
                        arrayList.add(movieAward);
                        count++;
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ctx,"Error ",Toast.LENGTH_SHORT);
                error.printStackTrace();
            }
        });
        MySingleton.getmInstance(ctx).addToRequestQueue(jsonArrayRequest);
        return arrayList;
    }
}
