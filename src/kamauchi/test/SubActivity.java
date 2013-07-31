package kamauchi.test;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SubActivity extends Activity implements OnClickListener {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sub_activity);

		Button okBtn = (Button) findViewById(R.id.ok_btn);
		Button cancelBtn = (Button) findViewById(R.id.cancel_btn);
		okBtn.setOnClickListener(this);
		cancelBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Log.d("CHECK", "start onClick()");
		switch (v.getId()) {
		case R.id.ok_btn:
			setResult(1);
			finish();
			break;
		case R.id.cancel_btn:
			setResult(0);
			finish();
			break;
		}
	}

}
