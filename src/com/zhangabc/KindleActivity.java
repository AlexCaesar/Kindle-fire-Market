package com.zhangabc;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.zhangabc.AutoLoadListener.AutoLoadCallBack;

public class KindleActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/** 全屏设置，隐藏窗口所有装饰 */
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		/** 标题是属于View的，所以窗口所有的修饰部分被隐藏后标题依然有效 */
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.relativemain);
		setTitle("");

		/** 一级分类 */
		final ImageButton ibgame = (ImageButton) findViewById(R.id.btngame);
		final ImageButton ibapp = (ImageButton) findViewById(R.id.btnapp);
		final ImageButton ibneed = (ImageButton) findViewById(R.id.btnneed);
		final ImageButton ibother = (ImageButton) findViewById(R.id.btnother);
		final View[] btns = new View[] { ibgame, ibapp, ibneed, ibother };
		final int[] res = new int[] { R.drawable.top_game, R.drawable.top_app,
				R.drawable.top_need, R.drawable.top_other };
		ibgame.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				changeBackGround(btns, res);
				ibgame.setBackgroundResource(R.drawable.top_game_selected);
			}
		});
		ibapp.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				changeBackGround(btns, res);
				ibapp.setBackgroundResource(R.drawable.top_app_selected);
			}
		});
		ibneed.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				changeBackGround(btns, res);
				ibneed.setBackgroundResource(R.drawable.top_need_selected);
			}
		});
		ibother.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				changeBackGround(btns, res);
				ibother.setBackgroundResource(R.drawable.top_other_selected);
			}
		});

		/** 二级分类 */
		List<Map<String, String>> data = new ArrayList<Map<String, String>>();
		for (int i = 0; i < 10; i++) {
			Map<String, String> m = new HashMap<String, String>();
			m.put("key", "key" + i);
			m.put("value", "value" + i);
			data.add(m);
		}

		SimpleAdapter sa = new SimpleAdapter(this, data, R.layout.listitem,
				new String[] { "key", "value" }, new int[] { R.id.key,
						R.id.value });

		ListView lv = (ListView) findViewById(R.id.menu);
		lv.setAdapter(sa);
		lv.setOnItemClickListener(new MenuItemClickListener());

		/** 应用列表 */
		GridView gv = (GridView) findViewById(R.id.gv);
		List<Map<String, String>> gvdata = new ArrayList<Map<String, String>>();
		for (int i = 1; i < 8; i++) {
			Map<String, String> m = new HashMap<String, String>();
			m.put("key", "" + getResId("sample_" + i));
			m.put("value", "value" + i);
			gvdata.add(m);
		}
		gvdata.clear();
		SimpleAdapter gvsa = new SimpleAdapter(this, gvdata, R.layout.appitem,
				new String[] { "key", "value", "value" }, new int[] {
						R.id.ItemImage, R.id.ItemText, R.id.ItemText2 });
		AutoLoadListener autoLoadListener = new AutoLoadListener(callBack);
		gv.setOnScrollListener(autoLoadListener);
		gv.setAdapter(gvsa);
		gv.setOnItemClickListener(new MenuItemClickListener());
	}

	public int getResId(String key) {
		try {
			Field f = R.drawable.class.getField(key);
			return f.getInt(new R.drawable());
		} catch (Exception e) {
			Log.e("92Kindle", "ResId >>>" + key + "<<< not found");
			return R.drawable.ic_launcher;
		}
	}

	AutoLoadCallBack callBack = new AutoLoadCallBack() {
		public void execute(String url) {
			Toast.makeText(KindleActivity.this, url, 500).show();
		}
	};

	// 当AdapterView被单击(触摸屏或者键盘)，则返回的Item单击事件
	class MenuItemClickListener implements OnItemClickListener {
		public void onItemClick(AdapterView<?> o0, View o1, int o2, long o3) {
			Map<?, ?> item = (Map<?, ?>) o0.getItemAtPosition(o2);
			setTitle(item.get("value").toString());
		}
	}

	private static void changeBackGround(View[] v, int[] res) {
		if (v.length == res.length) {
			for (int i = 0; i < res.length; i++) {
				v[i].setBackgroundResource(res[i]);
			}
		}
	}
}
