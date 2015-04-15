/**
 * File Splash.java
 * Proj calc
 * Date 2014��12��26�� ����5:08:26
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
 * ����������������ʾ
 * @author sanko
 */
public class Splash extends Activity implements OnClickListener {
	
	/**
	 * �Ϸ�����
	 */
	TextView t = null;
	/**
	 * �·�С��
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
		str += "MORE ON   ��";
		
		t.setText(str); 
		
		//�����Ļֱ�ӽ���������
		t.setOnClickListener(this);
		c.setOnClickListener(this);
		
		//���������Զ�����������
		h.postDelayed(r, 4000);		
		
	}
	
	@Override
	public void onClick(View arg0) {
		
		Intent intent = new Intent(Splash.this, MainActivity.class);
		startActivity(intent);
		h.removeCallbacks(r);//�������֮����ֹͣh�ļ�ʱ
		Splash.this.finish();
	}

}
