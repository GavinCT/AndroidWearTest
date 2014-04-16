package com.czt.androidweartest;

import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preview.support.v4.app.NotificationManagerCompat;
import android.preview.support.wearable.notifications.RemoteInput;
import android.preview.support.wearable.notifications.WearableNotifications;
import android.preview.support.wearable.notifications.WearableNotifications.Action;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.BigTextStyle;
import android.view.View;

public class MainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	public void onClick(View view){
		switch (view.getId()) {
		case R.id.send_normal:
			sendNormal();
			break;
		case R.id.send_big:
			sendBig();
			break;
		case R.id.send_normal_hide_icon:
			sendNormalHideIcon();
			break;
		case R.id.send_input:
			sendInput();
			break;
		case R.id.send_input2:
			sendInput2();
			break;
		case R.id.send_add_page:
			addPage();
			break;
		case R.id.send_set_group:
			setGroup();
			break;
		default:
			break;
		}
	}
	private void sendNormal(){
		int notificationId = 001;
		// Build intent for notification content
		Intent viewIntent = new Intent(this, ViewEventActivity.class);
//		viewIntent.putExtra(EXTRA_EVENT_ID, eventId);
		PendingIntent viewPendingIntent =
		        PendingIntent.getActivity(this, 0, viewIntent, 0);
		NotificationCompat.Builder notificationBuilder =
		        new NotificationCompat.Builder(this)
		        .setSmallIcon(R.drawable.ic_launcher)
		        .setLargeIcon(BitmapFactory.decodeResource(
		                getResources(), R.drawable.background))
		        .setContentTitle("标题：奶茶妹")
		        .setContentText("XXXXXXXXX...\n ")
		        .setContentIntent(viewPendingIntent)
		        .addAction(R.drawable.ic_launcher,
                "查看更多", viewPendingIntent);

		// Get an instance of the NotificationManager service
		NotificationManagerCompat notificationManager =
		        NotificationManagerCompat.from(this);

		// Build the notification and issues it with notification manager.
		notificationManager.notify(notificationId, notificationBuilder.build());
	}
	private void sendNormalHideIcon(){
		int notificationId = 003;
		// Build intent for notification content
		Intent viewIntent = new Intent(this, ViewEventActivity.class);
//		viewIntent.putExtra(EXTRA_EVENT_ID, eventId);
		PendingIntent viewPendingIntent =
				PendingIntent.getActivity(this, 0, viewIntent, 0);
		NotificationCompat.Builder notificationBuilder =
				new NotificationCompat.Builder(this)
		.setSmallIcon(R.drawable.ic_launcher)
		.setLargeIcon(BitmapFactory.decodeResource(
				getResources(), R.drawable.background))
				.setContentTitle("标题：奶茶妹")
				.setContentText("XXXXXXXXX...\n ")
				.setContentIntent(viewPendingIntent)
				.addAction(R.drawable.ic_launcher,
						"查看更多", viewPendingIntent);
		
		Notification notification =
		        new WearableNotifications.Builder(notificationBuilder)
		        .setHintHideIcon(true)
		        .build();
		// Get an instance of the NotificationManager service
		/*
		 * If you instead use the framework's NotificationManager, 
		 * some features from WearableNotifications.Builder will not work
		 */
		NotificationManagerCompat notificationManager =
				NotificationManagerCompat.from(this);
		
		// Build the notification and issues it with notification manager.
		notificationManager.notify(notificationId, notification);
		
	}
	private void sendBig(){
		int notificationId = 002;
		// Build intent for notification content
		Intent viewIntent = new Intent(this, ViewEventActivity.class);
//		viewIntent.putExtra(EXTRA_EVENT_ID, eventId);
		PendingIntent viewPendingIntent =
				PendingIntent.getActivity(this, 0, viewIntent, 0);
		
		
		BigTextStyle bigStyle = new NotificationCompat.BigTextStyle();
		bigStyle.bigText("我是长文章\nxxxx\nxxxx\nxxxx\nxxxx\nxxxx\nxxxx\nxxxx\nxxxx\nxxxx\nxxxx\nxxxx\n");
		
		NotificationCompat.Builder notificationBuilder =
				new NotificationCompat.Builder(this)
		.setSmallIcon(R.drawable.ic_launcher)
		.setLargeIcon(BitmapFactory.decodeResource(
				getResources(), R.drawable.background2))
				.setContentTitle("文章")
				//设置长文章后设置此参数无效。
				.setContentText("XXXXXXXXX...\n ")
				.setContentIntent(viewPendingIntent)
				.addAction(R.drawable.ic_launcher,
						"姚笛新闻在这里", viewPendingIntent)
				.addAction(R.drawable.ic_launcher,
						"马伊俐新闻在这里", viewPendingIntent)
				.setStyle(bigStyle);
		
		// Get an instance of the NotificationManager service
		NotificationManagerCompat notificationManager =
				NotificationManagerCompat.from(this);
		
		// Build the notification and issues it with notification manager.
		notificationManager.notify(notificationId, notificationBuilder.build());
	}
	// Key for the string that's delivered in the action's intent
	private static final String EXTRA_VOICE_REPLY = "extra_voice_reply";
	private void sendInput(){
		// Create intent for reply action
		Intent replyIntent = new Intent(this, ViewEventActivity.class);
		PendingIntent replyPendingIntent =
		        PendingIntent.getActivity(this, 0, replyIntent, 0);

		// Build the notification
		NotificationCompat.Builder replyNotificationBuilder =
		        new NotificationCompat.Builder(this)
		        .setSmallIcon(R.drawable.ic_launcher)
		        .setContentTitle("Message from Travis")
		        .setContentText("I love key lime pie!")
		        .setContentIntent(replyPendingIntent);

		String[] replyChoices = getResources().getStringArray(R.array.reply_choices);
		// Create the remote input
		RemoteInput remoteInput = new RemoteInput.Builder(EXTRA_VOICE_REPLY)
		        .setLabel("报告老板")
		        .setChoices(replyChoices)
		        .build();

		// Create wearable notification and add remote input
		Notification replyNotification =
		        new WearableNotifications.Builder(replyNotificationBuilder)
		        .addRemoteInputForContentIntent(remoteInput)
		        .build();
		
		// Get an instance of the NotificationManager service
		NotificationManagerCompat notificationManager =
				NotificationManagerCompat.from(this);
		
		// Build the notification and issues it with notification manager.
		notificationManager.notify(4, replyNotification);
	}
	private void sendInput2(){
		// Create intent for reply action
		Intent replyIntent = new Intent(this, ViewEventActivity.class);
		PendingIntent replyPendingIntent =
		        PendingIntent.getActivity(this, 0, replyIntent, 0);

		// Build the notification
		NotificationCompat.Builder replyNotificationBuilder =
		        new NotificationCompat.Builder(this)
		        .setSmallIcon(R.drawable.ic_launcher)
		        .setContentTitle("Message from Travis")
		        .setContentText("I love key lime pie!")
		        .setContentIntent(replyPendingIntent);

		String[] replyChoices = getResources().getStringArray(R.array.reply_choices);
		// Create the remote input
		RemoteInput remoteInput = new RemoteInput.Builder(EXTRA_VOICE_REPLY)
		        .setLabel("报告老板")
		        .setChoices(replyChoices)
		        .build();
		// Create the notification action and add remote input
		Action replyAction = new Action.Builder(R.drawable.ic_launcher,
		        "Reply", replyPendingIntent)
		        .addRemoteInput(remoteInput)
		        .build();

		// Create wearable notification and add action
		Notification replyNotification =
		        new WearableNotifications.Builder(replyNotificationBuilder)
		        .addAction(replyAction)
		        .build();
		// Get an instance of the NotificationManager service
		NotificationManagerCompat notificationManager =
				NotificationManagerCompat.from(this);
		
		// Build the notification and issues it with notification manager.
		notificationManager.notify(5, replyNotification);
	}
	private void addPage(){
		// Create intent for reply action
		Intent replyIntent = new Intent(this, ViewEventActivity.class);
		PendingIntent replyPendingIntent =
		        PendingIntent.getActivity(this, 0, replyIntent, 0);
		// Create builder for the main notification
		NotificationCompat.Builder notificationBuilder =
		        new NotificationCompat.Builder(this)
		        .setSmallIcon(R.drawable.ic_launcher)
		        .setContentTitle("Page 1")
		        .setContentText("Short message")
		        .setContentIntent(replyPendingIntent);

		// Create a big text style for the second page
		BigTextStyle secondPageStyle = new NotificationCompat.BigTextStyle();
		secondPageStyle.setBigContentTitle("Page 2")
		               .bigText("A lot of text...");

		// Create second page notification
		Notification secondPageNotification =
		        new NotificationCompat.Builder(this)
		        .setStyle(secondPageStyle)
		        .build();

		// Create main notification and add the second page
		Notification twoPageNotification =
		        new WearableNotifications.Builder(notificationBuilder)
		        .addPage(secondPageNotification)
		        .build();
		// Get an instance of the NotificationManager service
		NotificationManagerCompat notificationManager =
				NotificationManagerCompat.from(this);
		
		// Build the notification and issues it with notification manager.
		notificationManager.notify(6, twoPageNotification);
	}
	final static String GROUP_KEY_EMAILS = "group_key_emails";
	int i = 0;
	/**
	 * 似乎有bug
	 */
	private void setGroup(){
		NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
		         .setContentTitle("New mail from kk"+ i)
		         .setContentText("xxxxx")
		         .setSmallIcon(R.drawable.ic_launcher);
		Notification notif = new WearableNotifications.Builder(builder)
		.setGroup(GROUP_KEY_EMAILS, WearableNotifications.GROUP_ORDER_SUMMARY)
		.build();
		NotificationCompat.Builder builder2 = new NotificationCompat.Builder(this)
		.setContentTitle("New mail from kk2"+ i)
		.setContentText("xxxxx")
		.setSmallIcon(R.drawable.ic_launcher);
		Notification notif2 = new WearableNotifications.Builder(builder2)
		.setGroup(GROUP_KEY_EMAILS, WearableNotifications.GROUP_ORDER_SUMMARY)
		.build();
		// Get an instance of the NotificationManager service
		NotificationManagerCompat notificationManager =
				NotificationManagerCompat.from(this);
		
		// Build the notification and issues it with notification manager.
		notificationManager.notify(7, notif);
		notificationManager.notify(8, notif2);
	}
}
