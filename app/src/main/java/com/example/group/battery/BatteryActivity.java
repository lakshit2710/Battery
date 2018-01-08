package com.example.group.battery;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class BatteryActivity extends AppCompatActivity {
    private BroadcastReceiver br = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int level = intent.getIntExtra("level", 0);


            TextView tv = (TextView) findViewById(R.id.textView2);
            tv.setText("Battery level:" + Integer.toString(level) + "%");

            if (level < 10) {
                Toast.makeText(BatteryActivity.this, "You have low battery", Toast.LENGTH_SHORT).show();
                final AlertDialog.Builder alertBuilder =new AlertDialog.Builder(BatteryActivity.this);
                alertBuilder.setTitle("low Battery");
                alertBuilder.setMessage("You have low battery, please recharge battery!");
                alertBuilder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();

                    }
                });
                AlertDialog ad=alertBuilder.create();
                ad.show();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery);
    registerReceiver(br,new IntentFilter(
            Intent.ACTION_BATTERY_CHANGED));

    }
}
