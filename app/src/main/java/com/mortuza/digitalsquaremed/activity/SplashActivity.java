package com.mortuza.digitalsquaremed.activity;

import android.app.AlarmManager;
import android.app.Fragment;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mortuza.digitalsquaremed.broadcast.BroadcastManager;
import com.mortuza.digitalsquaremed.R;
import com.mortuza.digitalsquaremed.fragments.MRDialogFragment;

import java.util.Calendar;

public class SplashActivity extends AppCompatActivity implements MRDialogFragment.DialogListener {
    Button button;
    final public static String ONE_TIME = "onetime";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        button = findViewById(R.id.GO);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startAlert();
                DialogFragment numberpicker = new MRDialogFragment();
                numberpicker.show(SplashActivity.this.getSupportFragmentManager(), "NoticeDialogFragment");
            }
        });

    }

    public void startAlert(int hourOfDay, int minute) {
        Calendar calNow = Calendar.getInstance();
        Calendar calSet = (Calendar) calNow.clone();

        calSet.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calSet.set(Calendar.MINUTE, minute);
        calSet.set(Calendar.SECOND, 0);
        calSet.set(Calendar.MILLISECOND, 0);

        if (calSet.compareTo(calNow) <= 0) {
            // Today Set time passed, count to tomorrow
            calSet.add(Calendar.DATE, 1);
        }
        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, BroadcastManager.class);
        intent.putExtra(ONE_TIME, Boolean.FALSE);
        PendingIntent pi = PendingIntent.getBroadcast(this, 0, intent, 0);
        am.set(AlarmManager.RTC_WAKEUP,calSet.getTimeInMillis() , pi);
        am.setRepeating(AlarmManager.RTC_WAKEUP,calSet.getTimeInMillis(),2*1000 , pi);
        Toast.makeText(this, "Alarm set successfully", Toast.LENGTH_SHORT).show();
    }

    public void setOnetimeTimer(Context context) {
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, BroadcastManager.class);
        intent.putExtra(ONE_TIME, Boolean.TRUE);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);
        am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), pi);
    }

    @Override
    public void getBack(int hour, int min) {
        Toast.makeText(this, "" + hour + "=" + min, Toast.LENGTH_SHORT).show();
        startAlert(hour, min);
    }
}
