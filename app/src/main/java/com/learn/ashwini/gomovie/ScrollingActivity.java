package com.learn.ashwini.gomovie;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ScrollingActivity extends AppCompatActivity {

    //declare buttons
    Button button;
    Button parseJsonButton;
    EditText githubUsername;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        button = (Button)findViewById(R.id.imageButton);
        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(ScrollingActivity.this,
                                "Fetching Image...", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(ScrollingActivity.this, ShowImage.class));
                    }
                }
        );
        parseJsonButton = (Button)findViewById(R.id.parseJson);
        githubUsername = (EditText)findViewById(R.id.githubUsername);
        //final String username = githubUsername.getText().toString();
        parseJsonButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String username = githubUsername.getText().toString();
                        Toast.makeText(ScrollingActivity.this,
                                username, Toast.LENGTH_LONG).show();
                        Bundle bundle = new Bundle();//Create a new bundle..
                        bundle.putString("username",username);
                        Intent i = new Intent(ScrollingActivity.this,parseJson.class);
                        i.putExtras(bundle);
                        startActivity(i);
                    }
                }
        );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
