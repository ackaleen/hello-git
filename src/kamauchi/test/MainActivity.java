package kamauchi.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	int requestCode = 1;
	Button startButton;
	Button stopButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button btnDisp = (Button) findViewById(R.id.btnDisp);
		// Button btnDisp2 = (Button)findViewById(R.id.btnSqliteTest);

		btnDisp.setOnClickListener(this);

		findViews();
		startButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startService(new Intent(getBaseContext(), MainService.class));

			}
		});

		stopButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				stopService(new Intent(getBaseContext(), MainService.class));
			}
		});

		// btnDisp2.setOnClickListener(new OnClickListener() {
		// public void onClick(View v) {
		// // Sub 画面を起動
		// // インテントの生成
		// Intent intent = new Intent();
		// intent.setClassName("kamauchi.test", "kamauchi.test.SQLiteTest");
		// startActivity(intent);
		// }
		// });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);

		// String message = "";
		// String testFileName = "test01.data";
		// String testinputText = "Hello world";
		//
		// try {
		// FileOutputStream outStream = openFileOutput(testFileName,
		// MODE_PRIVATE);
		// OutputStreamWriter writer = new OutputStreamWriter(outStream);
		// writer.write(testinputText);
		// writer.flush();
		// writer.close();
		//
		// message = "File saved.";
		// } catch (FileNotFoundException e) {
		// message = e.getMessage();
		// e.printStackTrace();
		// } catch (IOException e) {
		// message = e.getMessage();
		// e.printStackTrace();
		// }
		//
		// Toast.makeText(getBaseContext(), message, Toast.LENGTH_LONG).show();
		//
		return true;
	}

	@Override
	public void onClick(View v) {
		Log.d("CHECK", "start onClick()");
		switch (v.getId()) {
		case R.id.btnDisp:
			startActivityForResult(new Intent(this, SubActivity.class),
					requestCode);
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == this.requestCode) {
			if (resultCode == 1) {
				Toast.makeText(this, "OKがクリックされました", Toast.LENGTH_LONG).show();
			} else {
				Toast.makeText(this, "Cancelがクリックされました", Toast.LENGTH_LONG)
						.show();
			}
		}
	}

	protected void findViews() {
		startButton = (Button) findViewById(R.id.startBtn);
		stopButton = (Button) findViewById(R.id.stopBtn);
	}
}
