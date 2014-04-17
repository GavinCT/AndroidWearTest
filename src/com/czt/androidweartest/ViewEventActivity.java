package com.czt.androidweartest;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

public class ViewEventActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_event);
		TextView tv = ((TextView)findViewById(R.id.textView1));
		getIntentContentAndSet(MainActivity.EXTRA_VOICE_REPLY, tv);
		getIntentContentAndSet(MainActivity.EXTRA_EVENT_ID, tv);
	}

	private void getIntentContentAndSet(String name, TextView tv) {
		String text = getIntent().getStringExtra(name);
		if(!TextUtils.isEmpty(text)){
			if("呵呵".equals(text)){
				text = "“听说你搞 IT 的？负责什么？”\n“研发”\n “哇！产品核心啊！”；\n “设计师” \n“卧槽，项目灵魂啊！”； \n“运营” \n“赞啊，勤劳的小蜜蜂！” ；\n“产品经理” \n“烧死他丫，就这孩子没事天天瞎 JB 改版！”\n" + text;
			}
			tv.setText(text);
		}
	}
}
