/**
 * File HelpActivity.java
 * Proj calc
 * Date 2014年12月26日 下午5:55:34
 */
package com.example.calc;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * 帮助页面
 * @author sanko 
 */
public class HelpActivity extends Activity {
	
	/**
	 * 上方主体区域，帮助内容
	 */
	TextView t = null;
	/**
	 * 底部按钮
	 */
	Button b = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.help);

		t = (TextView) findViewById(R.id.help_content);
		b = (Button) findViewById(R.id.back);
		
		String str = "FOR SANKO \nHAPPY B~DAY\n\n";
		str += "向左滑动 《《 \n不仅仅是个计算器\n\n";
		str += "点击三角形 ▲ \n选择远超想象\n\n";
		str += "网络通信不畅\n多按几次 CALC";
		
		t.setText(str);
		
		b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
	        	HelpActivity.this.finish();
			}
		});
		
	}
	
}
