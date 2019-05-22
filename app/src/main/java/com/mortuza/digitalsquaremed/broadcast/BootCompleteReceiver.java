package com.mortuza.digitalsquaremed.broadcast;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.mortuza.digitalsquaremed.activity.AlarmActivity;

public class BootCompleteReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Alarm IS SHOWS", Toast.LENGTH_LONG).show();
        long k = PreferenceManager.getDefaultSharedPreferences(context).getLong("long", 0);
        if (k == 0) return;
        alarmReset(context, k);
        //context.startActivity(new Intent(context, AlarmActivity.class));
    }

    public void alarmReset(Context context, long x) {
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, BroadcastManager.class);
        intent.putExtra("one_power", Boolean.FALSE);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);
        am.set(AlarmManager.RTC_WAKEUP, x, pi);
    }
}
