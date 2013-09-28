package com.example.drinkcount;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class DrinkCounter extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_drink_counter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.drink_counter, menu);
		return true;
	}

}
