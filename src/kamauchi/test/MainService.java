package kamauchi.test;

import android.R.string;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

public class MainService extends IntentService {

	private Context mContext;
	private Handler mHandler;
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
				// TODO Auto-generated method stub
				Toast.makeText(mContext, "MainService is running", Toast.LENGTH_LONG).show();
			}
		});
	}

	@Override
	public void onCreate() {
		super.onCreate();
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
