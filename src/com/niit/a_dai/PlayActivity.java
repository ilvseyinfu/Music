package com.niit.a_dai;

import java.io.BufferedInputStream;
import java.io.InputStream;

import com.niit.a_dai.R;

import android.R.color;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class PlayActivity extends Activity{

	LinearLayout layout;
	Button play,pause,stop,more,local;
	static TextView list;
	TextView lyrics,back;
	Intent intent;
	static RadioGroup  radiogroup;
	static RadioButton xunhuan,suiji,liebiao;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play);
		
		layout=(LinearLayout)findViewById(R.id.play_layout);
		
		radiogroup=(RadioGroup)findViewById(R.id.play_radioGroup);
		xunhuan=(RadioButton)findViewById(R.id.play_radio_xunhuan);
		liebiao=(RadioButton)findViewById(R.id.play_radio_liebiao);
		suiji=(RadioButton)findViewById(R.id.play_radio_suiji);
		
		play=(Button)findViewById(R.id.play_button_play);
		pause=(Button)findViewById(R.id.play_button_pause);
		stop=(Button)findViewById(R.id.play_button_stop);
		more=(Button)findViewById(R.id.play_button_more);
		local=(Button)findViewById(R.id.play_button_local);
		//注册上下文菜单
		registerForContextMenu(more);
		
		Listener l = new Listener();
		play.setOnClickListener(l);
		pause.setOnClickListener(l);
		stop.setOnClickListener(l);
		local.setOnClickListener(l);
		
		
		list=(TextView)findViewById(R.id.play_textView_list);
		lyrics=(TextView)findViewById(R.id.play_textView_lyrics);
		back=(TextView)findViewById(R.id.play_textView_back);
		
		intent = new Intent(PlayActivity.this,PlayService.class);
		
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				lyrics.setText("歌词");
				
			}
		});
		
		

		
		list.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			Intent intent = new Intent(PlayActivity.this,MusicListActivity.class);
				startActivityForResult(intent, 100);
			}
		});
		

	}
	
	
	
	
	
	
	// 当创建上下文菜单事被调用，执行填充菜单项的代码
	 @Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		getMenuInflater().inflate(R.menu.main, menu);
		
	}
	 

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case R.id.internet:
			Toast.makeText(PlayActivity.this, "在线搜索", Toast.LENGTH_LONG).show();
			break;
		case R.id.change:
			Toast.makeText(PlayActivity.this, "跟换主题", Toast.LENGTH_LONG).show();
			
			break;
		
		}
		
		
		return true;
	}

	class Listener implements OnClickListener{

		@Override
		public void onClick(View v) {
			switch(v.getId()){
			case R.id.play_button_play:
				Toast.makeText(PlayActivity.this, "开始播放", Toast.LENGTH_LONG).show();
				show_lyrics(list.getText().toString());
				startService(intent);
				break;
			case R.id.play_button_pause:
				PlayService.play.pause();
				Toast.makeText(PlayActivity.this, "暂停播放", Toast.LENGTH_LONG).show();
				break;
			case R.id.play_button_stop:
				stopService(intent);
				Toast.makeText(PlayActivity.this, "停止播放", Toast.LENGTH_LONG).show();
				break;
			case R.id.play_button_local:
				Intent intent = new Intent(PlayActivity.this,MusicMessageActivity.class);
				startActivity(intent); // 跳转到歌曲详细信息界面
				break;
			}
			
		}
		 
		 
	 }

		protected void onActivityResult(int requestCode,int resultCode, Intent data) {
			super.onActivityResult(requestCode, resultCode, data);
			//判断结果码和请求码是否正确
			if(requestCode==100&&resultCode==RESULT_OK){
				Bundle bundle=data.getExtras(); //获取传输意图包含的数据包
				list.setText(bundle.getString("music")); //根据键获取内容
			}
		}
	 
		
		public static int find_music(String musicname){
			if(musicname.equals("红玫瑰"))
			{
				return R.raw.music_1;
			}
			else if(musicname.equals("娱乐天空"))
			{
				return R.raw.music_2;
			}	
			else if(musicname.equals("那年今日"))
			{
				return R.raw.music_3;
			}
			else if(musicname.equals("阴天快乐"))
			{
				return R.raw.music_4;
			}
			else if(musicname.equals("梦想天灯2016"))
			{
				return R.raw.music_5;
			}
			else if(musicname.equals("陪你度过漫长岁月"))
			{
				return R.raw.music_6;
			}
			else if(musicname.equals("圣诞结"))
			{
				return R.raw.music_7;
			}
			else  return R.raw.music_1;
			
		}
		public void show_lyrics(String musicname){
			if(musicname.equals("红玫瑰"))
			{
				read(R.raw.music_1_lyrics);
			}
			else if(musicname.equals("娱乐天空"))
			{
				read(R.raw.music_2_lyrics);
			}	
			else if(musicname.equals("那年今日"))
			{
				read(R.raw.music_3_lyrics);
			}
			else if(musicname.equals("阴天快乐"))
			{
				read(R.raw.music_4_lyrics);
			}
			else if(musicname.equals("梦想天灯2016"))
			{
				read(R.raw.music_5_lyrics);
			}
			else if(musicname.equals("陪你度过漫长岁月"))
			{
				read(R.raw.music_6_lyrics);
			}
			else if(musicname.equals("圣诞结"))
			{
				read(R.raw.music_7_lyrics);
			}
			else  read(R.raw.music_1_lyrics);
			
		}
		
		public void read(int id){
			InputStream is=null;
			BufferedInputStream bis=null;
			try{
			    is = getResources().openRawResource(id);
				bis = new  BufferedInputStream(is);
				byte[] b=new byte[bis.available()];
				bis.read(b);
				String s= new String(b);
				lyrics.setText(s);
				Toast.makeText(PlayActivity.this, "歌词显示成功", Toast.LENGTH_LONG).show();
			}catch(Exception e){
				Log.e("PlayActivity", e.getMessage());
			}finally{
		/*		if(bis!=null )
					try {
						bis.close();
					} catch (IOException e) {
						Log.e("PlayActivity", e.getMessage());
					}
				if(is!=null)
					try {
						is.close();
					} catch (IOException e) {
						Log.e("PlayActivity", e.getMessage());
					}*/
			}

			
			
		}
}
