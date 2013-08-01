package kamauchi.test;

import java.util.Timer;
import java.util.TimerTask;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

public class MainService extends IntentService implements SensorEventListener {

	private SensorManager mSensorManager;
	private Context mContext;
	private Handler mHandler;
	private Timer mTimer;
	final static String TAG = "TestService";

	public MainService(String name) {
		super(name);
	}
	
	public MainService(){
		super("MainService");
	}
	
	@Override
	protected void onHandleIntent(Intent intent){
		mContext = getApplicationContext();
		mHandler.post(new Runnable() {
			
			@Override
			public void run() {
				Toast.makeText(mContext, "MainService is running", Toast.LENGTH_LONG).show();
			}
		});
	}
	
	@Override
	public void onAccuracyChanged(Sensor sensor,int accuracy){
		
	}
	
	@Override
	public void onSensorChanged(SensorEvent event){
		//ここに加速度センサーのイベントを持ってくる
	}

	@Override
	public void onCreate() {
		super.onCreate();
		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		Log.d(TAG, "onCreate");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d(TAG, "onStartCommand");
		Toast.makeText(this, "Hello from Service!", Toast.LENGTH_SHORT).show();
		return START_STICKY;
	}

	@Override
	public void onDestroy(){
		super.onDestroy();
		Log.d(TAG, "onDestroy");
	}
	
	

}
