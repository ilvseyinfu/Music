package com.niit.a_dai;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FindActivity extends Activity {

	EditText id,Email,Tel;
	Button confirm,back;
	TextView show;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_find);
		//寻找组件EditText
		id=(EditText)findViewById(R.id.find_editText_id);
		Email=(EditText)findViewById(R.id.find_editText_Email);
		Tel=(EditText)findViewById(R.id.find_editText_Phonenumber);
		//寻找组件Button
		confirm=(Button)findViewById(R.id.find_button_confirm);
		back=(Button)findViewById(R.id.find_button_back);
		//寻找组件TextView
		show=(TextView)findViewById(R.id.find_textView_show);
		//注册监听器
		Listener l= new Listener();
		confirm.setOnClickListener(l);
		back.setOnClickListener(l);
		
	}
	class Listener implements OnClickListener{

		@Override
		public void onClick(View v) {
			switch(v.getId()){
			case R.id.find_button_back:
				FindActivity.this.finish(); //当前活动结束
				break;
			case R.id.find_button_confirm:
				findPassword(id.getText().toString(),Email.getText().toString(),Tel.getText().toString());
				break;
			}
		}

	}																		 
	public void findPassword(String username,String email,String tel){
		String sql="select * from userMessage where Username=? and Email=? and Phonenumber=?";
		String[] args={username,email,tel};		
		Cursor c=MainActivity.db.rawQuery(sql, args);
		if(c.moveToFirst())
		{
			String message=c.getString(2);
			show.setText("原密码找回： "+message+"\n");
			show.append("下次要记住密码哦 ~亲");
		}
		else
		{
			show.setText("我们已经尽力了，再好好想想吧~亲");
		}
		
	}
	
	
}
