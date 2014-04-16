package com.czt.androidweartest;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preview.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.BigTextStyle;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findViewById(R.id.send_message).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				sendMessage();
			}
		});
	}
	private void sendMessage(){
		int notificationId = 001;
		// Build intent for notification content
		Intent viewIntent = new Intent(this, ViewEventActivity.class);
//		viewIntent.putExtra(EXTRA_EVENT_ID, eventId);
		PendingIntent viewPendingIntent =
		        PendingIntent.getActivity(this, 0, viewIntent, 0);

		
		BigTextStyle bigStyle = new NotificationCompat.BigTextStyle();
		bigStyle.bigText("我是长文章");
		
		NotificationCompat.Builder notificationBuilder =
		        new NotificationCompat.Builder(this)
		        .setSmallIcon(R.drawable.ic_launcher)
		        .setLargeIcon(BitmapFactory.decodeResource(
		                getResources(), R.drawable.background_activity_welcome))
		        .setContentTitle("我是标题")
		        .setContentText("我是内容")
		        .setContentIntent(viewPendingIntent)
		        .addAction(R.drawable.ic_launcher,
                "自己点开", viewPendingIntent)
                .setStyle(bigStyle);

		// Get an instance of the NotificationManager service
		NotificationManagerCompat notificationManager =
		        NotificationManagerCompat.from(this);

		// Build the notification and issues it with notification manager.
		notificationManager.notify(notificationId, notificationBuilder.build());
	}
}
