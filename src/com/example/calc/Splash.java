/**
 * File Splash.java
 * Proj calc
 * Date 2014年12月26日 下午5:08:26
 */
package com.example.calc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

/**
 * 闪屏，给出操作提示
 * @author sanko
 */
public class Splash extends Activity implements OnClickListener {
	
	/**
	 * 上方大字
	 */
	TextView t = null;
	/**
	 * 下方小字
	 */
	TextView c = null;

	private Handler h = new Handler();
	private Runnable r = new Runnable() {
		
		@Override
		public void run() {
			Intent intent = new Intent(Splash.this, MainActivity.class);
			startActivity(intent);
			Splash.this.finish();
			
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);

		t = (TextView) findViewById(R.id.content);
		c = (TextView) findViewById(R.id.cc);
		
		c.setText("click to skip\ncalculator sanko dec 2014");
		
		String str = "GET HELP IN \nMENU\n\n";
		str += "SWIPE IT   <<<\n\n";
		str += "MORE ON   ▲";
		
		t.setText(str); 
		
		//点击屏幕直接进入主界面
		t.setOnClickListener(this);
		c.setOnClickListener(this);
		
		//四秒闪屏自动进入主界面
		h.postDelayed(r, 4000);		
		
	}
	
	@Override
	public void onClick(View arg0) {
		
		Intent intent = new Intent(Splash.this, MainActivity.class);
		startActivity(intent);
		h.removeCallbacks(r);//点击进入之后则停止h的计时
		Splash.this.finish();
	}

}
