package com.codekul.notifications;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnToast).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(MainActivity.this,"this is toast",Toast.LENGTH_LONG);
                TextView textView = new TextView(MainActivity.this);
                textView.setLayoutParams(new LinearLayout.LayoutParams(500,100));
                textView.setBackgroundColor(Color.RED);
                textView.setText("Custom Toast");
                toast.setView(textView);
                //toast.setDuration(10);
                toast.show();
            }
        });

        findViewById(R.id.btnStatusBarNotification).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent action = new Intent(MainActivity.this,NotificationACtivity.class);
                PendingIntent intent = PendingIntent.getActivity(MainActivity.this,100,action,PendingIntent.FLAG_NO_CREATE);

                NotificationCompat.Builder builder =
                        new NotificationCompat.Builder(MainActivity.this)
                        .setContentTitle("Content Title")
                        .setContentText("Content Text")
                        .setContentInfo("Content Info")
                        .setDefaults(Notification.DEFAULT_ALL)
                        .setContentIntent(intent)
                        .setSmallIcon(R.mipmap.ic_launcher).setColor(Color.BLUE)
                        .setShowWhen(true)
                        .addAction(R.mipmap.ic_launcher,"Sports",intent)
                        .addAction(R.mipmap.ic_launcher,"News",intent);

                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    manager.notify(1456,builder.build());
                }
            }
        });
    }
}
