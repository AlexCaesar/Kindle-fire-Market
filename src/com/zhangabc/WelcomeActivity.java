package com.zhangabc;

import com.zhangabc.KindleActivity;
import com.zhangabc.R;
import com.zhangabc.WelcomeActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;

public class WelcomeActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.welcome);
		setTitle("92kindle电子市场");
		Button btnWel = (Button) findViewById(R.id.btnInto);
		btnWel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(WelcomeActivity.this, KindleActivity.class);
				startActivity(intent);
				WelcomeActivity.this.finish();
			}
		});
    }
}
