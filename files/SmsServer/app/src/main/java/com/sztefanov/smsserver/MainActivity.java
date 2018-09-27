package com.sztefanov.smsserver;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sztefanov.smsserver.network.Server;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = MainActivity.class.getSimpleName();
    public static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 1;

    private Server server;
    private boolean server_on = false;
    private Button server_switch;

    /**
     * Checks whether the app has SMS permission.
     */
    private void checkForSmsPermission() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, getString(R.string.permission_not_granted));
            // Permission not yet granted. Use requestPermissions().
            // MY_PERMISSIONS_REQUEST_SEND_SMS is an
            // app-defined int constant. The callback method gets the
            // result of the request.
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.SEND_SMS},
                    MY_PERMISSIONS_REQUEST_SEND_SMS);
        } else {
            // Permission already granted. Enable the SMS button.
            Log.i("permission", "permission ok");
        }
    }

    @SuppressLint("SetTextI18n")
    private void server_switch(TextView ip) {
        if (!server_on) {
            server = new Server(ip);
            server.start_network();
            server_on = true;
            server_switch.setText("Quit");
        } else {
            server.stop_network();
            server_on = false;
            server_switch.setText("Start");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[], @NonNull int[] grantResults) {
        // For the requestCode, check if permission was granted or not.
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (permissions[0].equalsIgnoreCase(Manifest.permission.SEND_SMS)
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkForSmsPermission();
        setContentView(R.layout.activity_main);
        final TextView ip = findViewById(R.id.ip);
        server_switch = findViewById(R.id.sever_switch);
        server_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                server_switch(ip);
            }
        });

    }

}
