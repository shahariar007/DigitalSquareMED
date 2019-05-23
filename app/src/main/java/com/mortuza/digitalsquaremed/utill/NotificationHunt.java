package com.mortuza.digitalsquaremed.utill;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.mortuza.digitalsquaremed.R;
import com.mortuza.digitalsquaremed.activity.SplashActivity;

import static android.app.Notification.EXTRA_NOTIFICATION_ID;
import static android.content.Context.NOTIFICATION_SERVICE;

public class NotificationHunt {
    Context mContext;

    public NotificationHunt(Context mContext) {
        this.mContext = mContext;
    }

    public void show() {
        NotificationManager manager = (NotificationManager) mContext.getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext);
        long when = System.currentTimeMillis();
        builder.setSmallIcon(R.drawable.ic_launcher_background);
        Intent notificationIntent = new Intent(mContext, SplashActivity.class).putExtra("notification", "1");
        PendingIntent contentIntent = PendingIntent.getActivity(mContext, 1, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);
        Notification notification = builder.getNotification();
        notification.when = when;

        RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(), R.layout.notification_view);
        remoteViews.setTextViewText(R.id.tvName, "New Name");
        listener(remoteViews,mContext,manager);


        notification.contentView = remoteViews;
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        manager.notify(1, notification);
    }
    public void listener(RemoteViews remoteViews, Context context, final NotificationManager manager) {
        // you have to make intetns for each action (your Buttons)
        Intent intent = new Intent("Accept");
        Intent intent2 = new Intent("Reject");

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,1,intent,0);
        PendingIntent pendingIntent2 = PendingIntent.getBroadcast(context,1,intent2,0);

        // add actions here !
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("Accept");
        intentFilter.addAction("Reject");


        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if(intent.getAction().equals("Accept")){
                    Toast.makeText(context, "Accepted !!", Toast.LENGTH_SHORT).show();
                } else if(intent.getAction().equals("Reject")) {
                    Toast.makeText(context, "Rejected !!", Toast.LENGTH_SHORT).show();
                }
                manager.cancel(1);
            }
        };

        context.registerReceiver(receiver,intentFilter);
        remoteViews.setOnClickPendingIntent(R.id.ivRequest,pendingIntent);
        remoteViews.setOnClickPendingIntent(R.id.ivReject,pendingIntent2);

    }
}
