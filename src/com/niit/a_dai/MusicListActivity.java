package com.niit.a_dai;



import com.niit.a_dai.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class MusicListActivity extends Activity{

	ListView musiclist;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_musiclist);
		
		musiclist=(ListView)findViewById(R.id.musiclist_listView);
		
		musiclist.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				//获取选中位置对应的选项，转成字符串
				String music=musiclist.getItemAtPosition(position).toString();
				Intent intent=new Intent(); //创建一个意图，用于传输数据
				Bundle bundle=new Bundle(); //创建一个数据包
				bundle.putString("music", music); //向数据包放置一个数据，键-值对的形式放置
				intent.putExtras(bundle); //将数据包放入意图
				setResult(RESULT_OK,intent); //设置结果的结果码，以及传输结果用的intent
				MusicListActivity.this.finish(); //结束当前活动
				
			}
		});
		
	}

		
	
}
