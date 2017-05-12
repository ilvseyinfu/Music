package com.niit.a_dai;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class SQLHelper extends SQLiteOpenHelper{

	public SQLHelper(Context context){
		super(context,"A_Dai.db",null,1);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql2="create table musicMessage(id integer primary key ,musicname text, author text , album text )";
		String sql="create table userMessage(id integer primary key ,Username text not null, Password text not null, Email text not null ,Phonenumber text not null)";
		db.execSQL(sql);
		db.execSQL(sql2);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	
		
	}

	
}
