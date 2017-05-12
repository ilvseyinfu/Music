package com.niit.a_dai;

import com.niit.a_dai.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity{

	EditText id,email,password,tel,passwordagain;
	Button back,confirm;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
		//查找组件EditText
		id=(EditText)findViewById(R.id.register_editText_id);
		tel=(EditText)findViewById(R.id.register_editText_tel);
		email=(EditText)findViewById(R.id.register_editText_Email);
		password=(EditText)findViewById(R.id.register_editText_password);
		passwordagain=(EditText)findViewById(R.id.register_editText_passwordAgain);
		//查找组件Button
		back=(Button)findViewById(R.id.register_button_back);
		confirm=(Button)findViewById(R.id.register_button_confirm);
		//注册监视器
		Listener l = new Listener();
		back.setOnClickListener(l);
		confirm.setOnClickListener(l);
		
	}
	class Listener implements OnClickListener{
		@Override
		public void onClick(View v) {
			switch(v.getId()){
			case R.id.register_button_back:
				RegisterActivity.this.finish();
				break;
			case R.id.register_button_confirm:
				if(password.getText().toString().equals(passwordagain.getText().toString()))
				 registerInfo(id.getText().toString(),email.getText().toString(),tel.getText().toString(),password.getText().toString());
				else
				{
					Toast.makeText(RegisterActivity.this, "两次密码不一致，请重新输入", Toast.LENGTH_LONG).show();
				}
				break;
			}
		}
		
	}
	public void registerInfo(String username,String email,String phoneNumber,String password){
		String sql="insert into userMessage(Username, Password,Email,Phonenumber) values(?,?,?,?)";
		String[] args={username,email,phoneNumber,password};
		MainActivity.db.execSQL(sql, args);
		Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_LONG).show();
		
	}
	
}
