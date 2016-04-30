package com.learn.ashwini.gomovie;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.URL;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class parseJson extends AppCompatActivity {
    TextView repo,location,user,follower;
    Button showRepo;
    ImageView img;
    Bitmap bitmap;
    private ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

    /*
        Defining onClicks

    */








        img = (ImageView) findViewById(R.id.img);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parse_json);
        Bundle bundle = getIntent().getExtras();
        //Extract the data..
        String username = bundle.getString("username");

        String url = "https://api.github.com/users/".concat(username);

        progress = new ProgressDialog(parseJson.this);
        progress.setMessage("Fetching data");
        progress.show();
        JsonObjectRequest jsonRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.print(response);
                        // the response is already constructed as a JSONObject!
                        try {
                            int repos = -1;
                            repos = response.getInt("public_repos");
                            int followers = response.getInt("followers");
                            int following = response.getInt("following");
                            String githubUsername = response.getString("login");
                            String name = response.getString("name");
                            String userLocation = response.getString("location");
                            String imgUrl = response.getString("avatar_url");



//                            JSONArray res=response.getJSONArray("results");
                            repo=(TextView)findViewById(R.id.info_text);
                            repo.setText(Integer.toString(repos));
                            location=(TextView)findViewById(R.id.location);
                            location.setText(userLocation.substring(0, 1).toUpperCase() + userLocation.substring(1));
                            user = (TextView)findViewById(R.id.username);
                            user.setText(name);
                            follower=(TextView)findViewById(R.id.followerText);
                            follower.setText(Integer.toString(followers));


                            /*Toast.makeText(parseJson.this,
                                    Integer.toString(repos), Toast.LENGTH_LONG).show();*/
//                            System.out.println("Site: "+site+"\nNetwork: "+network);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        progress.dismiss();
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        Volley.newRequestQueue(this).add(jsonRequest);
    }
    //LoadImage class declare and define karte hain.

}
