package com.hanheng.matrix;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.hanheng.a53.dotarray.DotArrayClass;
import com.hanheng.a53.dotarray.FontClass;
import com.ourselec.matrix11.R;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;


import java.io.Console;
import java.lang.reflect.Array;
import java.util.Arrays;
import javax.security.auth.PrivateCredentialPermission;
import com.hanheng.a53.dip.DipClass;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Handler.Callback;
import android.provider.MediaStore.Video;
import android.R.integer;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.graphics.Color;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends Activity implements OnClickListener{
	public int i;
	public int index;
	public float leftvol,rightvol;
	private GridView gView;
	private List<FillContent> data_List=new ArrayList<FillContent>();
	private EditText text;
	private Button button1;
	private Button button2;
	private Button button3;
	private Button button4;
	private Button button5;
	private Button button6;
	private Button button7;
	private boolean flag;
	public String test;
	private int[] icon={R.drawable.blue,R.drawable.orange};
	private MyAdapter adapter;
	
	
	MediaPlayer mediaPlayer = new MediaPlayer();
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
		initData(icon);
//		初始化适配器
		adapter=new MyAdapter(MainActivity.this, R.layout.item,data_List);
		gView.setAdapter(adapter);
		button1.setOnClickListener(this);
		button2.setOnClickListener(this);
		button3.setOnClickListener(this);
		button4.setOnClickListener(this);
		button5.setOnClickListener(this);
		button6.setOnClickListener(this);
		button7.setOnClickListener(this);
		if (mediaPlayer!=null&&mediaPlayer.isPlaying()) {
			mediaPlayer.stop();
		}
		Context aContext = getApplicationContext();
		mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.one);
		index=0;
		leftvol=1;
		rightvol=1;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public int findindex(int index)
	{
		if(index==0)
		{
			return R.raw.one;
		}
		else if(index==1)
		{
			return R.raw.two;
		}
		else if(index==2)
		{
			return R.raw.three;
		}
		return R.raw.one;
	}
	
	public void showSound(int raw) {
		if (mediaPlayer!=null&&mediaPlayer.isPlaying()) {
			mediaPlayer.stop();
		}
		Context aContext = getApplicationContext();
		mediaPlayer = MediaPlayer.create(getApplicationContext(), raw);
		mediaPlayer.setVolume(leftvol,rightvol);
		mediaPlayer.start();

	}
	
	void show(int num)
	{
		MediaPlayer myplayer=new MediaPlayer();
		String path=Environment.getExternalStorageDirectory().getAbsolutePath()+"/yinpin/"+num +".mp3";
		Log.e("path", path);
			try {
				myplayer.setDataSource(path);
				myplayer.prepareAsync();
				myplayer.start();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
	
	
	
	
	
	
//	初始化标签组件
	private void init(){
		this.text=(EditText)findViewById(R.id.editText1);
		gView=(GridView)findViewById(R.id.gview1);
		button1=(Button)findViewById(R.id.button1);
		button2=(Button)findViewById(R.id.button2);
		button3=(Button)findViewById(R.id.button3);
		button4=(Button)findViewById(R.id.button4);
		button5=(Button)findViewById(R.id.button5);
		button6=(Button)findViewById(R.id.button6);
		button7=(Button)findViewById(R.id.button7);
		DipClass.Init();
		openThread();
		FontClass.getInstance();
	}
	
	public void computed(int val){
		String str=addZero(val);
		char[] cr=str.toCharArray();
		int tag;
		for(int i=0;i<cr.length;i++){
			if(cr[i]=='0'){
				tag=0;
				changeState(i,tag);
			}else{		
				changeState(i,1);				
			}
		}
		test=str;
		
	}
	public boolean tag1=true;
	public boolean tag2=true;
	public boolean tag3=true;
	public boolean tag4=true;
	public boolean tag5=true;
	public boolean tag6=true;
	public boolean tag7=true;
	
	public void changeState(int i,int tag){
		if(tag==0){
			switch (i) {
			case 0:
				if(tag1==true)
				{
				String str1 ="播放";
				if(str1.length()!=0){
					byte[][] data = FontClass.getInstance().setContent(str1,this.getAssets());
					icon = getIcon(data[0]);
//					清空数据
					adapter.clear();
//					初始化数据
					initData(icon);
//					通知数据改变
					adapter.notifyDataSetChanged();
					//show(1);
					mediaPlayer.start();
					}
				}
				tag1=false;
				//tb8.setChecked(true);				
				break;
			case 1:
				if
				(tag2==true){
				String str4 ="下一首";
				if(str4.length()!=0){
					byte[][] data = FontClass.getInstance().setContent(str4,this.getAssets());
					icon = getIcon(data[0]);
//					清空数据
					adapter.clear();
//					初始化数据
					initData(icon);
//					通知数据改变
					adapter.notifyDataSetChanged();
//					show(1);
					index=(index+1)%3;
					showSound(findindex(index));
					}
				}
				tag2=false;
				//tb7.setChecked(true);				
				break;
			case 2:
				if(tag3==true)
				{
					String str3 ="上一首";
					if(str3.length()!=0){
						byte[][] data = FontClass.getInstance().setContent(str3,this.getAssets());
						icon = getIcon(data[0]);
//						清空数据
						adapter.clear();
//						初始化数据
						initData(icon);
//						通知数据改变
						adapter.notifyDataSetChanged();
						index=(index-1+3)%3;
						showSound(findindex(index));
						}
				}
				tag3=false;
				//tb6.setChecked(true);				
				break;
			case 3:
				if(tag4==true)
				{
					String str2 ="暂停";
					if(str2.length()!=0){
						byte[][] data = FontClass.getInstance().setContent(str2,this.getAssets());
						icon = getIcon(data[0]);
//						清空数据
						adapter.clear();
//						初始化数据
						initData(icon);
//						通知数据改变
						adapter.notifyDataSetChanged();
						mediaPlayer.pause();
						}
				}
				tag4=false;
				//tb5.setChecked(true);				
				break;
			case 4:
				if(tag5==true)
				{
					String str5 ="音量加";
					if(str5.length()!=0){
						byte[][] data = FontClass.getInstance().setContent(str5,this.getAssets());
						icon = getIcon(data[0]);
//						清空数据
						adapter.clear();
//						初始化数据
						initData(icon);
//						通知数据改变
						adapter.notifyDataSetChanged();
						if(rightvol<1&&leftvol<1)
						{
							rightvol+=0.1;
							leftvol+=0.1;
						}
						mediaPlayer.setVolume(leftvol, rightvol);
					}	
				}
				tag5=false;
				//tb4.setChecked(true);				
				break;
			case 5:
				if(tag6==true)
				{
					String str6 ="音量减";
					if(str6.length()!=0){
						byte[][] data = FontClass.getInstance().setContent(str6,this.getAssets());
						icon = getIcon(data[0]);
//						清空数据
						adapter.clear();
//						初始化数据
						initData(icon);
//						通知数据改变
						adapter.notifyDataSetChanged();
						if(rightvol>0&&leftvol>0)
						{
							rightvol-=0.1;
							leftvol-=0.1;
						}
						mediaPlayer.setVolume(leftvol, rightvol);
					}
				}
				tag6=false;
				//tb3.setChecked(true);				
				break;
			case 6:
				if(tag7==true)
				{
					String str7 ="退出";
					if(str7.length()!=0){
						byte[][] data = FontClass.getInstance().setContent(str7,this.getAssets());
						icon = getIcon(data[0]);
//						清空数据
						adapter.clear();
//						初始化数据
						initData(icon);
//						通知数据改变
						adapter.notifyDataSetChanged();
			            AlertDialog isExit = new AlertDialog.Builder(this).create();  
			            isExit.setTitle("系统提示");  
			            isExit.setMessage("确定要退出吗");  
			            isExit.setButton("确定", listener);  
			            isExit.setButton2("取消", listener);  
			            isExit.show();
					}
				}
				tag7=false;
				//tb2.setChecked(true);				
				break;
			case 7:
				//tb1.setChecked(true);				
				break;
			default:
				break;
			}
		}else{
			switch (i) {
			case 0:
				tag1=true;
				//tb8.setChecked(false);				
				break;
			case 1:
				tag2=true;
				//tb7.setChecked(false);				
				break;
			case 2:
				tag3=true;
				//tb6.setChecked(false);				
				break;
			case 3:
				tag4=true;
				//tb5.setChecked(false);				
				break;
			case 4:
				//tb4.setChecked(false);	
				tag5=true;
				break;
			case 5:
				tag6=true;
				//tb3.setChecked(false);				
				break;
			case 6:
				tag7=true;
				//tb2.setChecked(false);				
				break;
			case 7:
				//tb1.setChecked(false);				
				break;
			default:
				break;
			}
		}
	}
	
//	鐎涙顑佹稉鑼端夐梿锟�
	public String addZero(int b){
		String val = Integer.toBinaryString(b&0xFF);
		String str="";
		if(val.length()<8){
			for(int i=0;i<8-val.length();i++){
				str+=0;
			}			
			return str+=val;
		}
		return val;
	}
	
//	初始化数据
	public void initData(int[] icon){
		for(int i=0;i<icon.length;i++){
			FillContent con=new FillContent(icon[i]);
			data_List.add(con);
		}
	}	
	@Override
	public void onClick(View arg0) {
		int key=arg0.getId(); 
		if(key==R.id.button1){
			String str1 ="播放";
			if(str1.length()!=0){
				byte[][] data = FontClass.getInstance().setContent(str1,this.getAssets());
				icon = getIcon(data[0]);
//				清空数据
				adapter.clear();
//				初始化数据
				initData(icon);
//				通知数据改变
				adapter.notifyDataSetChanged();
				//show(1);
				mediaPlayer.start();
				}
		}
			else if(key==R.id.button2)	{
				String str2 ="暂停";
				if(str2.length()!=0){
					byte[][] data = FontClass.getInstance().setContent(str2,this.getAssets());
					icon = getIcon(data[0]);
//					清空数据
					adapter.clear();
//					初始化数据
					initData(icon);
//					通知数据改变
					adapter.notifyDataSetChanged();
					mediaPlayer.pause();
					}
			}
				else if(key==R.id.button3)	{
					String str3 ="上一首";
					if(str3.length()!=0){
						byte[][] data = FontClass.getInstance().setContent(str3,this.getAssets());
						icon = getIcon(data[0]);
//						清空数据
						adapter.clear();
//						初始化数据
						initData(icon);
//						通知数据改变
						adapter.notifyDataSetChanged();
						index=(index-1+3)%3;
						showSound(findindex(index));
						}
				}
				else if(key==R.id.button4)	{String str4 ="下一首";
				if(str4.length()!=0){
					byte[][] data = FontClass.getInstance().setContent(str4,this.getAssets());
					icon = getIcon(data[0]);
//					清空数据
					adapter.clear();
//					初始化数据
					initData(icon);
//					通知数据改变
					adapter.notifyDataSetChanged();
//					show(1);
					index=(index+1)%3;
					showSound(findindex(index));
					}
				}
				else if(key==R.id.button5)	{
					String str5 ="音量加";
					if(str5.length()!=0){
						byte[][] data = FontClass.getInstance().setContent(str5,this.getAssets());
						icon = getIcon(data[0]);
//						清空数据
						adapter.clear();
//						初始化数据
						initData(icon);
//						通知数据改变
						adapter.notifyDataSetChanged();
						if(rightvol<1&&leftvol<1)
						{
							rightvol+=0.1;
							leftvol+=0.1;
						}
						mediaPlayer.setVolume(leftvol, rightvol);
					}	
				}
				else if(key==R.id.button6)	{
					String str6 ="音量减";
					if(str6.length()!=0){
						byte[][] data = FontClass.getInstance().setContent(str6,this.getAssets());
						icon = getIcon(data[0]);
//						清空数据
						adapter.clear();
//						初始化数据
						initData(icon);
//						通知数据改变
						adapter.notifyDataSetChanged();
						if(rightvol>0&&leftvol>0)
						{
							rightvol-=0.1;
							leftvol-=0.1;
						}
						mediaPlayer.setVolume(leftvol, rightvol);
					}
				}
				else if(key==R.id.button7)	{
					String str7 ="退出";
					if(str7.length()!=0){
						byte[][] data = FontClass.getInstance().setContent(str7,this.getAssets());
						icon = getIcon(data[0]);
//						清空数据
						adapter.clear();
//						初始化数据
						initData(icon);
//						通知数据改变
						adapter.notifyDataSetChanged();
			            AlertDialog isExit = new AlertDialog.Builder(this).create();  
			            isExit.setTitle("系统提示");  
			            isExit.setMessage("确定要退出吗");  
			            isExit.setButton("确定", listener);  
			            isExit.setButton2("取消", listener);  
			            isExit.show();
					}
				}
				else{}
		}		

//	获得解析后的数组
	public int[] getIcon(byte[] data) {
		int[] arr = new int[256];
		int n = 0;
		for(int k=0; k<16; k++){
	        for(int j=0; j<2; j++){
	        	char[] cs = Integer.toBinaryString(data[k*2+j]&0xFF).toCharArray();
	            for(int i=0; i<8; i++){
	            	int len = cs.length;
	            	if(len+i<8){
	            		 arr[n] = R.drawable.blue;   	           		
	            	}else{
	            		if(cs[len+i-8]=='1'){
	            			arr[n] = R.drawable.orange;                        			
	            		}else{
	            			arr[n] = R.drawable.blue;  	            			
	            		}	            		
	            	}
	            	n++;
	            }	
	        }        
	    }
		return arr;
	}
	
	
	
	//	鍒濆鍖栨寜閽枃瀛�
	private Handler uiHandler = new Handler(){
		public void handleMessage(Message msg){
			if(msg.what==1){	
				Log.i("鑾峰彇鏁版嵁", ""+msg.arg1);
				computed(msg.arg1);
			}
		}
	};
    
	public void openThread(){
			if(!flag){
				MyThread thread=new MyThread();
				this.flag=true;
				thread.start();
			}		
	}
	//	璇诲彇瀛楃绾跨▼
	class MyThread extends Thread{
		public void run(){
			int num = 0;				
			while(flag){
				try {				
					Message msgMessage=new Message();
					int value=DipClass.ReadValue();
					msgMessage.what=1;
					msgMessage.arg1=value;
					uiHandler.sendMessage(msgMessage);
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}
	}		

	
	
	
	
	
	
	
	
	
	
	
	
	@SuppressWarnings("deprecation")
	@Override  
    public boolean onKeyDown(int keyCode, KeyEvent event){  
		if (keyCode == KeyEvent.KEYCODE_BACK ){  
            AlertDialog isExit = new AlertDialog.Builder(this).create();  
            isExit.setTitle("系统提示");  
            isExit.setMessage("确定要退出吗");  
            isExit.setButton("确定", listener);  
            isExit.setButton2("取消", listener);  
            isExit.show();    
        }  
		return false;  
	}  
	DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener(){  
		public void onClick(DialogInterface dialog, int which){  
			switch (which){  
			case AlertDialog.BUTTON_POSITIVE:// "确认"按钮退出程序  
				DotArrayClass.Exit();
				finish();   
				break;  
			case AlertDialog.BUTTON_NEGATIVE:// "取消"第二个按钮取消对话框  
				break;  
			default:  
				break;  
            }  
        }  
    };    
}



