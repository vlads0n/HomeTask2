package app.com.example.android.hometask2.broadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;


/**
 * Created by Влад on 09.11.2016.
 */
public class HeadsetReceiver extends BroadcastReceiver {
    boolean isConnected = false;
    @Override
    public void onReceive(Context context, Intent intent) {
        int state = intent.getIntExtra("state", -1);
        if (state == 1) {
            Toast.makeText(context, "Headset connected", Toast.LENGTH_LONG).show();
            isConnected = true;
        }
        else if (state == 0 && isConnected) {
            Toast.makeText(context, "Headset disconnected", Toast.LENGTH_LONG).show();
            isConnected = false;
        }
    }
}
