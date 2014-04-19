package com.czt.androidweartest;

import java.util.Random;

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
/**
 * adb -d forward tcp:5601 tcp:5601
 * @author tong
 *
 */
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
		case R.id.add_action:
			addAction();
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
		PendingIntent viewPendingIntent = getActivityPendingIntent("奶茶妹最新新闻：xxxx");
		NotificationCompat.Builder notificationBuilder =
		        new NotificationCompat.Builder(this)
		        .setSmallIcon(R.drawable.wiz_logo)   //不设置居然发不出去？？？？  只能通过下面的方式隐藏。
		        .setLargeIcon(BitmapFactory.decodeResource(
		                getResources(), R.drawable.background))
		        .setContentTitle("标题：奶茶妹")
		        .setContentText("XXXXXXXXX...\n ")
		        .setContentIntent(viewPendingIntent);

		notifyMessage(1, notificationBuilder.build());
	}
	private void sendNormalHideIcon(){
		PendingIntent viewPendingIntent = getActivityPendingIntent("奶茶妹最新新闻：xxxx");
		NotificationCompat.Builder notificationBuilder =
				new NotificationCompat.Builder(this)
		.setSmallIcon(R.drawable.wiz_logo)
		.setLargeIcon(BitmapFactory.decodeResource(
				getResources(), R.drawable.background))
				.setContentTitle("标题：奶茶妹")
				.setContentText("XXXXXXXXX...\n ")
				.setContentIntent(viewPendingIntent);
		
		Notification notification =
		        new WearableNotifications.Builder(notificationBuilder)
		        .setHintHideIcon(true)
		        .build();
		notifyMessage(2, notification);
		
	}
	private void addAction(){
		PendingIntent viewPendingIntent = getActivityPendingIntent("马伊俐最新新闻：xxxx");
		PendingIntent viewPendingIntent2 = getActivityPendingIntent("姚笛最新新闻：xxxx");
		
		
		BigTextStyle bigStyle = new NotificationCompat.BigTextStyle();
		bigStyle.bigText("我是长文章\nxxxx\nxxxx\nxxxx\nxxxx\nxxxx\nxxxx\nxxxx\nxxxx\nxxxx\nxxxx\nxxxx\n");
		
		NotificationCompat.Builder notificationBuilder =
				new NotificationCompat.Builder(this)
		.setSmallIcon(R.drawable.wiz_logo)
		.setLargeIcon(BitmapFactory.decodeResource(
				getResources(), R.drawable.background2))
				.setContentTitle("文章")
				//设置长文章后设置此参数无效。
				.setContentText("XXXXXXXXX...\n ")
				.setContentIntent(viewPendingIntent)
				.addAction(R.drawable.more,
						"姚笛新闻在这里", viewPendingIntent2)
				.addAction(R.drawable.more,
						"马伊俐新闻在这里", viewPendingIntent)
				.setStyle(bigStyle);
		notifyMessage(3, notificationBuilder.build());
	}
	private void addPage(){
		PendingIntent replyPendingIntent = getActivityPendingIntent("文章详细新闻");
		// Create builder for the main notification
		NotificationCompat.Builder notificationBuilder =
		        new NotificationCompat.Builder(this)
		        .setSmallIcon(R.drawable.wiz_logo)
		        .setContentTitle("文章")
		        .setContentText("xxxx\nxxxx\nxxxx\nxxxx\nxxxx\nxxxx\nxxxx\nxxxx\nxxxx\nxxxx\nxxxx\n")
		        .setContentIntent(replyPendingIntent);

		// Create a big text style for the second page
		BigTextStyle secondPageStyle = new NotificationCompat.BigTextStyle();
		secondPageStyle.setBigContentTitle("马伊俐")
		               .bigText("xxxx\nxxxx\nxxxx\nxxxx\nxxxx\nxxxx\nxxxx\nxxxx\nxxxx\nxxxx\nxxxx\n");

		// Create second page notification
		Notification secondPageNotification =
		        new NotificationCompat.Builder(this)
		        .setStyle(secondPageStyle)
		        .build();
		
		// Create a big text style for the second page
		BigTextStyle thirdPageStyle = new NotificationCompat.BigTextStyle();
		thirdPageStyle.setBigContentTitle("姚笛")
		.bigText("xxxx\nxxxx\nxxxx\nxxxx\nxxxx\nxxxx\nxxxx\nxxxx\nxxxx\nxxxx\nxxxx\n");
		
		// Create second page notification
		Notification thirdPageNotification =
				new NotificationCompat.Builder(this)
		.setStyle(thirdPageStyle)
		.build();

		// Create main notification and add the second page
		Notification pageNotification =
		        new WearableNotifications.Builder(notificationBuilder)
		        .addPage(secondPageNotification)
		        .addPage(thirdPageNotification)
		        .build();

		notifyMessage(6, pageNotification);
	}
	
	// Key for the string that's delivered in the action's intent
	public static final String EXTRA_VOICE_REPLY = "extra_voice_reply";
	/**
	 * Receive Voice Input for the Primary Action
	 */
	private void sendInput(){
		PendingIntent replyPendingIntent = getActivityPendingIntent("");

		// Build the notification
		NotificationCompat.Builder replyNotificationBuilder =
		        new NotificationCompat.Builder(this)
		        .setSmallIcon(R.drawable.wiz_logo)
		        .setLargeIcon(BitmapFactory.decodeResource(
		                getResources(), R.drawable.yxl))
		        .setContentTitle("来自yxl的信息")
		        .setContentText("下周改版")
		        .setContentIntent(replyPendingIntent);

		String[] replyChoices = getResources().getStringArray(R.array.reply_choices);
		// Create the remote input
		RemoteInput remoteInput = new RemoteInput.Builder(EXTRA_VOICE_REPLY)
		        .setLabel("回复")
		        .setChoices(replyChoices)
		        .build();

		// Create wearable notification and add remote input
		Notification replyNotification =
		        new WearableNotifications.Builder(replyNotificationBuilder)
		        .addRemoteInputForContentIntent(remoteInput)
		        .build();
		
		notifyMessage(4, replyNotification);
	}
	/**
	 * Receive Voice Input for a Secondary Action
	 */
	private void sendInput2(){
		PendingIntent replyPendingIntent = getActivityPendingIntent("");

		// Build the notification
		NotificationCompat.Builder replyNotificationBuilder =
		        new NotificationCompat.Builder(this)
		        .setSmallIcon(R.drawable.wiz_logo)
		        .setLargeIcon(BitmapFactory.decodeResource(
		                getResources(), R.drawable.yxl))
		        .setContentTitle("来自yxl的信息")
		        .setContentText("下周改版")
		        .setContentIntent(replyPendingIntent);

		String[] replyChoices = getResources().getStringArray(R.array.reply_choices);
		// Create the remote input
		RemoteInput remoteInput = new RemoteInput.Builder(EXTRA_VOICE_REPLY)
		        .setLabel("回复")
		        .setChoices(replyChoices)
		        .build();
		// Create the notification action and add remote input
		Action replyAction = new Action.Builder(R.drawable.comment,
		        "报告经理", replyPendingIntent)
		        .addRemoteInput(remoteInput)
		        .build();

		// Create wearable notification and add action
		Notification replyNotification =
		        new WearableNotifications.Builder(replyNotificationBuilder)
		        .addAction(replyAction)
		        .build();

		notifyMessage(5, replyNotification);
	}	
/*****************************************************************************************************/
	
	final static String GROUP_KEY_EMAILS = "group_key_emails";
	int i = 0;
	/**
	 * 似乎有bug
	 */
	private void setGroup(){
		NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
		         .setContentTitle("New mail from kk"+ i)
		         .setContentText("xxxxx")
		         .setSmallIcon(R.drawable.wiz_logo);
		Notification notif = new WearableNotifications.Builder(builder)
		.setGroup(GROUP_KEY_EMAILS, WearableNotifications.GROUP_ORDER_SUMMARY)
		.build();
		NotificationCompat.Builder builder2 = new NotificationCompat.Builder(this)
		.setContentTitle("New mail from kk2"+ i)
		.setContentText("xxxxx")
		.setSmallIcon(R.drawable.wiz_logo);
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
	
/*****************************************************************************************************/
	private void notifyMessage(int notificationId, Notification notification) {
		/*
		 * If you instead use the framework's NotificationManager, 
		 * some features from WearableNotifications.Builder will not work
		 */
		// Get an instance of the NotificationManager service
		NotificationManagerCompat notificationManager =
		        NotificationManagerCompat.from(this);
		// Build the notification and issues it with notification manager.
		notificationManager.notify(notificationId, notification);
	}
	//
	private int mRequestCode = 0 ;
	public static final String EXTRA_EVENT_ID = "context";
	private PendingIntent getActivityPendingIntent(String text) {
		// Build intent for notification content
		Intent viewIntent = new Intent(this, ViewEventActivity.class);
		viewIntent.putExtra(EXTRA_EVENT_ID, text);
		PendingIntent viewPendingIntent =
		        PendingIntent.getActivity(this,  mRequestCode, viewIntent, 0);
		mRequestCode++;
		return viewPendingIntent;
	}
}
