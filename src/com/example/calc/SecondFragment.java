/**
 * File SecondFragment.java
 * Proj calc
 * Date 2014年12月22日 下午9:11:39
 */
package com.example.calc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/** 
 * 调用百度进行汇率和进制转换
 * @author sanko
 */
public class SecondFragment extends Fragment implements OnClickListener {
	
	final int MSG_SUCCESS = 9999;
	
	/**
	 * 右上方，原始数值
	 */
    private TextView text1 = null;
    /**
     * 左上方，原始单位
     */
    private Button button1 = null;
    /**
     * 右下方，结果显示
     */
    private TextView text2 = null;
    /**
     * 左下方，目标单位
     */
    private Button button2 = null;
    /**
     * 用于网络请求的线程
     */
    private Thread mThread = null;
    /**
     * 单位名称，一行为一类
     */
    private static final String[][] s={
    	{"人民币","美元","日元","欧元","英镑","港币"},
    	{"千米","米","英寸","英尺","码","英里","海里","英寻","弗隆","里","丈","尺","寸"},
    	{"千克","克","吨","担","斤","两","磅","盎司","克拉","格令"},
    	{"立方米","升","英制加仑","美制加仑"},
    	{"摄氏度","华氏度","开氏度"},
    	{"帕斯卡","千帕","标准大气压","毫米汞柱","毫米水柱","公斤力/平方米"}
    };
    /**
     * 单位类型名称，序号与单位名称相应
     */
    String name[] = {"汇率","长度","重量","体积","温度","压强"};
    /**
     * 单位类型代码，即s和name的坐标
     */
    int mode = 0;
    /**
     * 用于组合URL的字符串
     */
    String sfrom = "";
    String sto = "";
    String urla = "http://m.baidu.com/s?word=";
    String urlb = " 多少";
	
	@Override
	public View onCreateView(LayoutInflater inflater, 
			ViewGroup container,Bundle savedInstanceState) {     
		View re = inflater.inflate(R.layout.fragment_second, container, false);
	
		text1 = (TextView)re.findViewById(R.id.textview1);
		button1 = (Button)re.findViewById(R.id.button1);
		text2 = (TextView)re.findViewById(R.id.textview2);
		button2 = (Button)re.findViewById(R.id.button2);
		
		Button b_n0 = (Button)re.findViewById(R.id.num0);
    	Button b_n1 = (Button)re.findViewById(R.id.num1);
    	Button b_n2 = (Button)re.findViewById(R.id.num2);
    	Button b_n3 = (Button)re.findViewById(R.id.num3);
    	Button b_n4 = (Button)re.findViewById(R.id.num4);
    	Button b_n5 = (Button)re.findViewById(R.id.num5);
    	Button b_n6 = (Button)re.findViewById(R.id.num6);
    	Button b_n7 = (Button)re.findViewById(R.id.num7);
    	Button b_n8 = (Button)re.findViewById(R.id.num8);
    	Button b_n9 = (Button)re.findViewById(R.id.num9);
    	Button b_clear = (Button)re.findViewById(R.id.clear);
    	Button b_del = (Button)re.findViewById(R.id.del);
    	Button b_channel = (Button)re.findViewById(R.id.channel);
    	Button b_calc = (Button)re.findViewById(R.id.calc);
    	Button b_dot = (Button)re.findViewById(R.id.dot);
    	
    	//“单位类型更改”按钮的颜色
    	b_channel.setBackgroundColor(Color.DKGRAY);
    	
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
    	b_clear.setOnClickListener(this);
    	b_del.setOnClickListener(this);
    	b_channel.setOnClickListener(this);
    	b_calc.setOnClickListener(this);
    	b_dot.setOnClickListener(this);
		
		button1.setOnClickListener(this);
		button2.setOnClickListener(this);
		
        return re;
    }  
	
	/**
	 * 处理按钮点击事件
	 */
	@Override
	public void onClick(View arg0) {
		
		final Button btn = (Button)arg0;
		
		if(btn.getText().charAt(0)=='F'
				||btn.getText().charAt(0)=='T'){	
			//点击button1或button2
			new AlertDialog.Builder(getActivity())
			.setTitle(name[mode])
			.setItems(s[mode],new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					if(btn.getText().toString().startsWith("F")){
						btn.setText("Fr "+s[mode][arg1]+" :");
						sfrom = s[mode][arg1];
					} else{
						btn.setText("To "+s[mode][arg1]+" :");
						sto = s[mode][arg1];
					}
					text2.setText("");
				}				
			})
			.show();
		}else if(btn.getText().equals("C")){
			text1.setText("0.00");
			text2.setText("");
		}else if(btn.getText().charAt(0)=='←'){
			if(text1.getText().equals("0.00")){
				text1.setText("");
			}
			if(text1.getText().length()==0)return;
			text1.setText(text1.getText().subSequence(0, text1.getText().length()-1));
		}else if(btn.getText().charAt(0)=='▲'){
			//单位类型更改
			new AlertDialog.Builder(getActivity())
			.setTitle("换算")
			.setItems(name,new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					mode = arg1;
					//转换后给出屏幕提示
					text2.setText("["+name[mode]+"换算]");
					//单位名称的初始化
					button1.setText("Fr 点击");
					button2.setText("To 点击");
				}				
			})
			.show();
		}else if(btn.getText().equals("CALC")){
			//如果text1没有正确输入，默认为1
			if(text1.getText().equals("0.00")||text1.getText().equals("")){
				text1.setText("1");
			}
			//如果button1没有进行选择，默认为第1项
			if(button1.getText().toString().contains("点击")){
				button1.setText("Fr "+s[mode][0]+" :");
				sfrom = s[mode][0];
			}
			//如果button2没有进行选择，默认为第2项
			if(button2.getText().toString().contains("点击")){				
				button2.setText("To "+s[mode][1]+" :");
				sto = s[mode][1];
			}
			//网络访问，获取结果
			mThread = new Thread(runnable);
			mThread.start();			
		}else{
			//处理数字输入
			if(text1.getText().equals("0.00"))text1.setText("");
			if(text1.getText().toString().contains(".")
					&&btn.getText().equals(".")){
				text2.setText("[已有小数点]");
				return;
			}
			if(text1.getText().length()>8){
				text2.setText("[数据过大]");
				return;
			}
			text1.setText(text1.getText().toString()+btn.getText().toString());
		}
	}
	
	@SuppressLint("HandlerLeak") 
	private Handler mHandler = new Handler() {		
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case MSG_SUCCESS:
				text2.setText(getVal((String)msg.obj));
			}
		}
	};
	
	/**
	 * 对获取到的网络数据进行处理
	 * @param de 网络返回的数据，已处理为UTF-8字符串
	 * @return 可供打印的结果字符串
	 */
	public String getVal(String de){
		  String oup = new String();
		  oup = de;
		  if(oup.isEmpty())return "[无网络]";
		  if(oup.startsWith("http"))return "[连接中]";
		  String buf = new String();
		  //处理汇率转换的情况
		  if(mode==0){
			  //除非DNS出错或百度页面更改，否则不会出现[不支持]返回值
			  int beg = oup.indexOf("兑换");
			  if(beg == -1)return "[不支持]";
			  String lab = sfrom + "</em>";
			  int i = oup.indexOf(lab, beg);
			  buf = "";
			  if(i == -1)return "[不支持]";
			  int j = oup.indexOf('=', i);
			  if(j == -1)return "[不支持]";
			  for(j += 1; oup.charAt(j) != '<'; j++){
				  buf += oup.charAt(j);
			  }
		  }
		  //处理单位换算的情况
		  else{			
			  //基本已覆盖测试，除非DNS出错或百度页面更改，否则不会出现[不支持]返回值
			  String lab = "度量衡换算";
			  int i = oup.indexOf(lab);
			  buf = "";
			  if(i == -1)return "[不支持]";			  
			  int j = oup.indexOf(sfrom,i);
			  if(j == -1)return "[不支持]";
			  for(j += sfrom.length()+1; oup.charAt(j) != sto.charAt(0); j++){
				  buf += oup.charAt(j);
			  }
			  //处理科学记数法
			  buf = buf.replace("*10<sup>", "E");
			  buf = buf.replace("</sup>", "");
			  buf = buf.replace("*10<sub>", "E");
			  buf = buf.replace("</sub>", "");
			  //处理三位分隔的空格
			  buf = buf.replace(" ", "");
		  }
		  //换算失败表明返回数据格式不正常。基本已覆盖测试，应当不会出现此情况
		  for(int i = 0;i < buf.length();i++){
			  char c = buf.charAt(i);
			  if('0'<=c&&c<='9'||c=='.'||c=='E'||c=='-');
			  else{
				  return "[换算失败]";
			  }
		  }

		  return buf;
	  }

	Runnable runnable = new Runnable() {

		@Override
		public void run() {

			String result = "";
			BufferedReader in = null;
			//对中文字符进行URL编码，urll存储需要访问的url地址
			String urll = text1.getText() + sfrom + urlb + sto;
			try {
				urll = URLEncoder.encode(urll, "utf-8");
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			urll = urla + urll;
			//处理完字符串后将URL先传回，表明开始进行连接
			mHandler.obtainMessage(MSG_SUCCESS, urll).sendToTarget();
			//http连接和返回数据处理
			try {
				String urlNameString = urll;
				URL realUrl = new URL(urlNameString);
				URLConnection connection = realUrl.openConnection();
				connection.setRequestProperty("DNT", "1");
				connection.setRequestProperty("connection", "Keep-Alive");
				connection.connect();
				in = new BufferedReader(new InputStreamReader(
						connection.getInputStream(), "utf-8"));
				String line;
				while ((line = in.readLine()) != null) {
					result += line;
				}
			} catch (Exception e) {
				System.out.println("发送GET请求出现异常！" + e);
				e.printStackTrace();
			} finally {
				try {
					if (in != null) {
						in.close();
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			//http返回数据传回
			mHandler.obtainMessage(MSG_SUCCESS, result).sendToTarget();
		}
	};
}
