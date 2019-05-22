package com.mortuza.digitalsquaremed.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.mortuza.digitalsquaremed.activity.AlarmActivity;

public class BroadcastManager extends BroadcastReceiver {
    private static final String TAG = "BroadcastManager";

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Alarm IS SHOWS", Toast.LENGTH_LONG).show();
        Log.d(TAG, "onReceive: " + intent.getExtras().getBoolean("onetime"));
        context.startActivity(new Intent(context, AlarmActivity.class));
    }
}
