package com.niit.a_dai;

import android.app.Service; 
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class PlayService extends Service{

	public static MediaPlayer play;
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		play=MediaPlayer.create(this,PlayActivity.find_music(PlayActivity.list.getText().toString()) );
		if(PlayActivity.xunhuan.isChecked())
			play.setLooping(true);
		super.onCreate();
		
	}

	//当服务被摧毁时，一般做资源释放工作用
	public void onDestroy() {
		super.onDestroy();
		play.stop(); // 服务停止时停止播放
	}

	//当服务被启动时，自动执行，启动你要执行的任务
	public int onStartCommand(Intent intent, int flags, int startId) {
		play.start();
		return super.onStartCommand(intent, flags, startId);
	}

}
