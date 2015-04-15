/**
 * File SecondFragment.java
 * Proj calc
 * Date 2014��12��22�� ����9:11:39
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
 * ���ðٶȽ��л��ʺͽ���ת��
 * @author sanko
 */
public class SecondFragment extends Fragment implements OnClickListener {
	
	final int MSG_SUCCESS = 9999;
	
	/**
	 * ���Ϸ���ԭʼ��ֵ
	 */
    private TextView text1 = null;
    /**
     * ���Ϸ���ԭʼ��λ
     */
    private Button button1 = null;
    /**
     * ���·��������ʾ
     */
    private TextView text2 = null;
    /**
     * ���·���Ŀ�굥λ
     */
    private Button button2 = null;
    /**
     * ��������������߳�
     */
    private Thread mThread = null;
    /**
     * ��λ���ƣ�һ��Ϊһ��
     */
    private static final String[][] s={
    	{"�����","��Ԫ","��Ԫ","ŷԪ","Ӣ��","�۱�"},
    	{"ǧ��","��","Ӣ��","Ӣ��","��","Ӣ��","����","ӢѰ","��¡","��","��","��","��"},
    	{"ǧ��","��","��","��","��","��","��","��˾","����","����"},
    	{"������","��","Ӣ�Ƽ���","���Ƽ���"},
    	{"���϶�","���϶�","���϶�"},
    	{"��˹��","ǧ��","��׼����ѹ","���׹���","����ˮ��","������/ƽ����"}
    };
    /**
     * ��λ�������ƣ�����뵥λ������Ӧ
     */
    String name[] = {"����","����","����","���","�¶�","ѹǿ"};
    /**
     * ��λ���ʹ��룬��s��name������
     */
    int mode = 0;
    /**
     * �������URL���ַ���
     */
    String sfrom = "";
    String sto = "";
    String urla = "http://m.baidu.com/s?word=";
    String urlb = " ����";
	
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
    	
    	//����λ���͸��ġ���ť����ɫ
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
	 * ����ť����¼�
	 */
	@Override
	public void onClick(View arg0) {
		
		final Button btn = (Button)arg0;
		
		if(btn.getText().charAt(0)=='F'
				||btn.getText().charAt(0)=='T'){	
			//���button1��button2
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
		}else if(btn.getText().charAt(0)=='��'){
			if(text1.getText().equals("0.00")){
				text1.setText("");
			}
			if(text1.getText().length()==0)return;
			text1.setText(text1.getText().subSequence(0, text1.getText().length()-1));
		}else if(btn.getText().charAt(0)=='��'){
			//��λ���͸���
			new AlertDialog.Builder(getActivity())
			.setTitle("����")
			.setItems(name,new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					mode = arg1;
					//ת���������Ļ��ʾ
					text2.setText("["+name[mode]+"����]");
					//��λ���Ƶĳ�ʼ��
					button1.setText("Fr ���");
					button2.setText("To ���");
				}				
			})
			.show();
		}else if(btn.getText().equals("CALC")){
			//���text1û����ȷ���룬Ĭ��Ϊ1
			if(text1.getText().equals("0.00")||text1.getText().equals("")){
				text1.setText("1");
			}
			//���button1û�н���ѡ��Ĭ��Ϊ��1��
			if(button1.getText().toString().contains("���")){
				button1.setText("Fr "+s[mode][0]+" :");
				sfrom = s[mode][0];
			}
			//���button2û�н���ѡ��Ĭ��Ϊ��2��
			if(button2.getText().toString().contains("���")){				
				button2.setText("To "+s[mode][1]+" :");
				sto = s[mode][1];
			}
			//������ʣ���ȡ���
			mThread = new Thread(runnable);
			mThread.start();			
		}else{
			//������������
			if(text1.getText().equals("0.00"))text1.setText("");
			if(text1.getText().toString().contains(".")
					&&btn.getText().equals(".")){
				text2.setText("[����С����]");
				return;
			}
			if(text1.getText().length()>8){
				text2.setText("[���ݹ���]");
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
	 * �Ի�ȡ�����������ݽ��д���
	 * @param de ���緵�ص����ݣ��Ѵ���ΪUTF-8�ַ���
	 * @return �ɹ���ӡ�Ľ���ַ���
	 */
	public String getVal(String de){
		  String oup = new String();
		  oup = de;
		  if(oup.isEmpty())return "[������]";
		  if(oup.startsWith("http"))return "[������]";
		  String buf = new String();
		  //�������ת�������
		  if(mode==0){
			  //����DNS�����ٶ�ҳ����ģ����򲻻����[��֧��]����ֵ
			  int beg = oup.indexOf("�һ�");
			  if(beg == -1)return "[��֧��]";
			  String lab = sfrom + "</em>";
			  int i = oup.indexOf(lab, beg);
			  buf = "";
			  if(i == -1)return "[��֧��]";
			  int j = oup.indexOf('=', i);
			  if(j == -1)return "[��֧��]";
			  for(j += 1; oup.charAt(j) != '<'; j++){
				  buf += oup.charAt(j);
			  }
		  }
		  //����λ��������
		  else{			
			  //�����Ѹ��ǲ��ԣ�����DNS�����ٶ�ҳ����ģ����򲻻����[��֧��]����ֵ
			  String lab = "�����⻻��";
			  int i = oup.indexOf(lab);
			  buf = "";
			  if(i == -1)return "[��֧��]";			  
			  int j = oup.indexOf(sfrom,i);
			  if(j == -1)return "[��֧��]";
			  for(j += sfrom.length()+1; oup.charAt(j) != sto.charAt(0); j++){
				  buf += oup.charAt(j);
			  }
			  //�����ѧ������
			  buf = buf.replace("*10<sup>", "E");
			  buf = buf.replace("</sup>", "");
			  buf = buf.replace("*10<sub>", "E");
			  buf = buf.replace("</sub>", "");
			  //������λ�ָ��Ŀո�
			  buf = buf.replace(" ", "");
		  }
		  //����ʧ�ܱ����������ݸ�ʽ�������������Ѹ��ǲ��ԣ�Ӧ��������ִ����
		  for(int i = 0;i < buf.length();i++){
			  char c = buf.charAt(i);
			  if('0'<=c&&c<='9'||c=='.'||c=='E'||c=='-');
			  else{
				  return "[����ʧ��]";
			  }
		  }

		  return buf;
	  }

	Runnable runnable = new Runnable() {

		@Override
		public void run() {

			String result = "";
			BufferedReader in = null;
			//�������ַ�����URL���룬urll�洢��Ҫ���ʵ�url��ַ
			String urll = text1.getText() + sfrom + urlb + sto;
			try {
				urll = URLEncoder.encode(urll, "utf-8");
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			urll = urla + urll;
			//�������ַ�����URL�ȴ��أ�������ʼ��������
			mHandler.obtainMessage(MSG_SUCCESS, urll).sendToTarget();
			//http���Ӻͷ������ݴ���
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
				System.out.println("����GET��������쳣��" + e);
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
			//http�������ݴ���
			mHandler.obtainMessage(MSG_SUCCESS, result).sendToTarget();
		}
	};
}
