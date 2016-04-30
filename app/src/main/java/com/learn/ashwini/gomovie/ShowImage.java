package com.learn.ashwini.gomovie;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.InputStream;
import java.net.URL;

public class ShowImage extends AppCompatActivity {
    ImageView img;
    Button fab;
    Bitmap bitmap;
    ProgressDialog pDialog;
    Button notification;

    @Override



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);

        notification = (Button) findViewById(R.id.notificationButton);
        notification.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                createNotification(view);
            }
        });


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        img = (ImageView)findViewById(R.id.img);
        new LoadImage().execute("https://www.winuall.com/uploads/IMG_20160428_083926~2.jpg");
        /*
        To load image from website... :P
        WebView web = (WebView) findViewById(R.id.web);
        web.loadUrl("https://www.winuall.com/uploads/IMG_20160428_083926~2.jpg");*/
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                startActivity(new Intent(ShowImage.this, parseJson.class));
            }
        });
    }

    //LoadImage class declare and define karte hain.
    private class LoadImage extends AsyncTask<String, String, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(ShowImage.this);
            pDialog.setMessage("Loading Image ....");
            pDialog.show();

        }

        protected Bitmap doInBackground(String... args) {
            try {
                bitmap = BitmapFactory.decodeStream((InputStream) new URL(args[0]).getContent());

            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        protected void onPostExecute(Bitmap image) {

            if (image != null) {
                img.setImageBitmap(image);
                pDialog.dismiss();
                Toast.makeText(ShowImage.this, "Aa gayi image! :P", Toast.LENGTH_SHORT).show();


            } else {

                pDialog.dismiss();
                Toast.makeText(ShowImage.this, "Image Does Not exist or Network Error", Toast.LENGTH_SHORT).show();

            }
        }
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void createNotification(View view) {
        // Prepare intent which is triggered if the
        // notification is selected
        Intent intent = new Intent(this, NotificationView.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);

        // Build notification
        // Actions are just fake
        Notification noti = new Notification.Builder(this)
                .setContentTitle("New pull request").setSmallIcon(R.drawable.logo)
                .setSmallIcon(R.drawable.logo)
                .setContentText("3 pull requests on GitIo").setSmallIcon(R.drawable.logo)
                .setContentIntent(pIntent).build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // hide the notification after its selected
        noti.flags |= Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0, noti);

    }
}