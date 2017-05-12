package com.niit.a_dai;

import com.niit.a_dai.R;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

    
    ImageView picture;
    public static SQLHelper helper;
    public static   SQLiteDatabase db;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        picture=(ImageView)findViewById(R.id.imageView1);
        
        helper = new SQLHelper(MainActivity.this);
        db = helper.getWritableDatabase();
        Toast.makeText(MainActivity.this, "用户数据库连接正常", Toast.LENGTH_LONG).show();
        
        picture.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,LoginActivity.class);
				startActivity(intent);
			}
		});

    }



}
