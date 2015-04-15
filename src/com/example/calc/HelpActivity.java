/**
 * File HelpActivity.java
 * Proj calc
 * Date 2014��12��26�� ����5:55:34
 */
package com.example.calc;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * ����ҳ��
 * @author sanko 
 */
public class HelpActivity extends Activity {
	
	/**
	 * �Ϸ��������򣬰�������
	 */
	TextView t = null;
	/**
	 * �ײ���ť
	 */
	Button b = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.help);

		t = (TextView) findViewById(R.id.help_content);
		b = (Button) findViewById(R.id.back);
		
		String str = "FOR SANKO \nHAPPY B~DAY\n\n";
		str += "���󻬶� ���� \n�������Ǹ�������\n\n";
		str += "��������� �� \nѡ��Զ������\n\n";
		str += "����ͨ�Ų���\n�ఴ���� CALC";
		
		t.setText(str);
		
		b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
	        	HelpActivity.this.finish();
			}
		});
		
	}
	
}
