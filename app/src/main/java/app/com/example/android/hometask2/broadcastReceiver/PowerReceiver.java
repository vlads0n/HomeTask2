package app.com.example.android.hometask2.broadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.widget.Toast;

/**
 * Created by Влад on 10.11.2016.
 */
public class PowerReceiver extends BroadcastReceiver {
    boolean isConnected = false;
    @Override
    public void onReceive(Context context, Intent intent) {
        int state = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        if (state == BatteryManager.BATTERY_STATUS_CHARGING) {
            Toast.makeText(context, "Battery charging", Toast.LENGTH_LONG).show();
            isConnected = true;
        }
        else if (state == BatteryManager.BATTERY_STATUS_DISCHARGING && isConnected) {
            Toast.makeText(context, "Battery discharging", Toast.LENGTH_LONG).show();
            isConnected = false;
        }
    }
}
