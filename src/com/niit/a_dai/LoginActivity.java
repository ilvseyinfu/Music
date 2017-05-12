package com.niit.a_dai;

import com.niit.a_dai.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity{

	
	Button login,register,find;
	EditText username, password;
	CheckBox remember;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		//寻找组件Button
		login=(Button)findViewById(R.id.login_button_login);
		find=(Button)findViewById(R.id.login_button_find);
		register=(Button)findViewById(R.id.login_button_register);
		//寻找组件EditText
		username=(EditText)findViewById(R.id.login_editText_id);
		password=(EditText)findViewById(R.id.login_editText_password);
		//寻找组件CheckBox
		remember=(CheckBox)findViewById(R.id.login_checkBox_remember);
		//用于记录密码
		rollup(); 
		//注册监听器
		listener click = new listener();
		login.setOnClickListener(click);
		find.setOnClickListener(click);
		register.setOnClickListener(click);
		
	}
		
	 class listener implements OnClickListener{

		@Override
		public void onClick(View v) {
			switch(v.getId()){
			
			case R.id.login_button_find:
				find();
				break;
			case R.id.login_button_login:
				login(username.getText().toString(), password.getText().toString());
				break;
			case R.id.login_button_register:
				register();
				break;
			}
		}
		 
	 }
	 
	 public void register(){
		 Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
		 startActivity(intent);
		 
	 }
	 
	 public void find(){
		 Intent intent = new Intent(LoginActivity.this,FindActivity.class);
		 startActivity(intent);
	 }
	 
	 public void login(String name, String password)
	 {
		 String sql="select * from userMessage where Username=? ";
		 String[] args={name};
		 Cursor c=MainActivity.db.rawQuery(sql, args);
		 if(c.moveToFirst())
		 {
			 if(c.getString(2).equals(password))
			 {
				 if(remember.isChecked())
				 {
					 SharedPreferences sp= getSharedPreferences("loginMessage", MODE_PRIVATE);
					 Editor editor = sp.edit();
					 editor.putString("username", username.getText().toString());
					 editor.putString("password", this.password.getText().toString());
					 editor.commit();
					 Toast.makeText(LoginActivity.this, "标记：记录成功", Toast.LENGTH_LONG).show();
				 }
				 Toast.makeText(LoginActivity.this, "标记：登录成功", Toast.LENGTH_LONG).show();
				 Intent intent = new Intent(LoginActivity.this,PlayActivity.class);
				 startActivity(intent);
			 }
			 else
			 {
				Toast.makeText(LoginActivity.this, "标记：密码错误", Toast.LENGTH_LONG).show();
			 }
			 
		 }
		 else
		 {
			 Toast.makeText(LoginActivity.this, "标记:无此账号", Toast.LENGTH_LONG).show();
			 
		 }
		 
	 }
	 public void rollup(){
		 SharedPreferences sp =getSharedPreferences("loginMessage", MODE_PRIVATE);
		 String message1 = sp.getString("username", null);
		 String message2 = sp.getString("password", null);
		 username.setText(message1);
		 password.setText(message2);
	 }
	 
	 
	
}
