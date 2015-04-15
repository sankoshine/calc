/**
 * File FirstFragment.java
 * Proj calc
 * Date 2014年12月22日 下午9:06:33
 */
package com.example.calc;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;
//Thanks Jep-Java-3.4
import com.singularsys.jep.Jep;
import com.singularsys.jep.JepException;
import com.singularsys.jep.bigdecimal.BigDecComponents;

/**
 * 计算器
 * @author sanko
 */
public class FirstFragment extends Fragment implements OnClickListener {

	/**
	 * 结果显示屏，下方大字
	 */
	TextView t_result = null;
	/**
	 * 表达式显示屏，上方小字
	 */
	TextView t_exp = null;
	/**
	 * 维护可直接计算的表达式
	 */
	String fexp = "";
	/**
	 * Jep的大数实现，主要用于解决浮点误差
	 */
	Jep eval = new Jep(new BigDecComponents());
	/**
	 * Jep的常规实现，主要用于显示大数、进行数学函数运算
	 */
	Jep eval2 = new Jep();
	
	@Override
	public View onCreateView(LayoutInflater inflater, 
			ViewGroup container,Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_first, container, false);
        
        t_result = (TextView)v.findViewById(R.id.result);
        t_exp = (TextView)v.findViewById(R.id.expression);
        
    	Button b_sqrt = (Button)v.findViewById(R.id.sqrt);
    	Button b_left = (Button)v.findViewById(R.id.left);
    	Button b_right = (Button)v.findViewById(R.id.right);
    	Button b_del = (Button)v.findViewById(R.id.del);
    	Button b_clear = (Button)v.findViewById(R.id.clear);
    	Button b_sin = (Button)v.findViewById(R.id.sin);
    	Button b_cos = (Button)v.findViewById(R.id.cos);
    	Button b_tan = (Button)v.findViewById(R.id.tan);
    	Button b_abs = (Button)v.findViewById(R.id.abs);
    	Button b_n0 = (Button)v.findViewById(R.id.num0);
    	Button b_n1 = (Button)v.findViewById(R.id.num1);
    	Button b_n2 = (Button)v.findViewById(R.id.num2);
    	Button b_n3 = (Button)v.findViewById(R.id.num3);
    	Button b_n4 = (Button)v.findViewById(R.id.num4);
    	Button b_n5 = (Button)v.findViewById(R.id.num5);
    	Button b_n6 = (Button)v.findViewById(R.id.num6);
    	Button b_n7 = (Button)v.findViewById(R.id.num7);
    	Button b_n8 = (Button)v.findViewById(R.id.num8);
    	Button b_n9 = (Button)v.findViewById(R.id.num9);
    	Button b_divide = (Button)v.findViewById(R.id.divide);
    	Button b_multiply = (Button)v.findViewById(R.id.multiply);
    	Button b_minus = (Button)v.findViewById(R.id.minus);
    	Button b_plus = (Button)v.findViewById(R.id.plus);
    	Button b_dot = (Button)v.findViewById(R.id.dot);
    	Button b_equal = (Button)v.findViewById(R.id.equal);
    	    	
    	b_sqrt.setOnClickListener(this);
    	b_left.setOnClickListener(this);
    	b_right.setOnClickListener(this);
    	b_del.setOnClickListener(this);
    	b_clear.setOnClickListener(this);
    	b_sin.setOnClickListener(this);
    	b_cos.setOnClickListener(this);
    	b_tan.setOnClickListener(this);
    	b_abs.setOnClickListener(this);
    	b_n0.setOnClickListener(this);
    	b_n1.setOnClickListener(this);
    	b_n2.setOnClickListener(this);
    	b_n3.setOnClickListener(this);
    	b_n4.setOnClickListener(this);
    	b_n5.setOnClickListener(this);
    	b_n6.setOnClickListener(this);
    	b_n7.setOnClickListener(this);
    	b_n8.setOnClickListener(this);
    	b_n9.setOnClickListener(this);
    	b_divide.setOnClickListener(this);
    	b_multiply.setOnClickListener(this);
    	b_minus.setOnClickListener(this);
    	b_plus.setOnClickListener(this);
    	b_dot.setOnClickListener(this);
    	b_equal.setOnClickListener(this);
    	
        return v;
    }   
	
	/**
	 * 处理按钮点击事件
	 */
	@Override
	public void onClick(View e) {
		
		//新建表达式，用于AC后和=后
		if(t_result.getText()!=""){
			t_result.setTextSize(50);
			t_exp.setText("");
			t_result.setText("");
			fexp = "";
		}
		
		//调取点击的按钮、表达式屏幕的内容
		Button btn = (Button)e;
		String exp = t_exp.getText().toString();
		
		if(btn.getText().equals("AC")){
			t_result.setText("0");
			fexp = "";
			exp = "";
		}
		else if(btn.getText().equals("←")){
			if(exp.isEmpty()){
				t_result.setText("0");
				return;
			}
			if(exp.endsWith("√(")){
				//2: √(
				exp = exp.substring(0, exp.length()-2);
				//5: sqrt(
				fexp = fexp.substring(0, fexp.length()-5);
			}
			else if(exp.endsWith("n(")||exp.endsWith("s(")){
				//4: sin( | cos( | tan( | abs(
				exp = exp.substring(0, exp.length()-4);
				fexp = fexp.substring(0, fexp.length()-4);
			}
			else{
				//常规字符，删除一位
				exp = exp.substring(0, exp.length()-1);
				fexp = fexp.substring(0, fexp.length()-1);
			}
		}
		else if(btn.getText().equals("√￣")){
			//自动加左括号
			exp += "√(";
			fexp += "sqrt(";
		}
		else if(btn.getText().toString().endsWith("n")
				||btn.getText().toString().endsWith("s")){
			//自动加左括号
			exp += btn.getText();
			exp += '(';
			fexp += btn.getText();
			fexp += '(';
		}
		else if(btn.getText().equals("÷")){
			exp += btn.getText();
			fexp += '/';
		}
		else if(btn.getText().equals("×")){
			exp += btn.getText();
			fexp += '*';
		}
		else if(btn.getText().equals("=")){
			
			//自动补全右括号
			int n = 0;
			for(int i=0;i<exp.length();i++){
				if(exp.charAt(i)=='(')n++;
				if(exp.charAt(i)==')')n--;
			}
			for(int i=0;i<n;i++){
				exp += ')';
				fexp += ')';
			}
			
			//运算
			exp += '=';
			String result = "";
			try{
				//先用大数算，排除整型溢出、浮点误差
				eval.parse(fexp);
				result = eval.evaluate().toString();
				//常规调用，对大数进行科学记数法调整
				eval2.parse(result);
				result = eval2.evaluate().toString();
			} catch(Exception err){
				try{
					//如果异常是因为大数运算中数学函数抛出的
					eval2.parse(fexp);
					result = eval2.evaluate().toString();
				} catch(JepException err2){
					result = "表达式出错";
				}
			}
			
			//结果处理
			if(result.endsWith(".0")){
				result = result.substring(0, result.length()-2);
			}else if(result.equals("NaN")){
				result = "零除溢出";
			}else if(result.endsWith("ty")){
				result = "零除溢出";//即正负infinity
			}else if(result.contains("(")){
				result = "开方错误";//即复数
			}
			
			//显示
			if(result.length()>15){
				t_result.setTextSize(25);
			}else if(result.length()>7){
				t_result.setTextSize(36);
			}
			t_result.setText(result);
		}
		else {
			//常规按钮，直接添加到表达式尾部
			exp += btn.getText();
			fexp += btn.getText();
		}		
		
		t_exp.setText(exp);		
	}	
}
