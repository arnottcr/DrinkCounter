package com.example.drinkcount;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.app.Activity;
import android.content.Context;
import java.util.Vector;

public class DrinkCounter extends Activity {

TextView text;
Vector<String> drinkList;
int counter=0;

private SensorManager mSensorManager;
private float mAccel; // acceleration apart from gravity
private float mAccelCurrent; // current acceleration including gravity
private float mAccelLast; // last acceleration including gravity
private long newTime=0;
private long oldTime=0;


 @Override
 public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  drinkList = new Vector<String>();
  setContentView(R.layout.activity_drink_counter);
  
  mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
  mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
  mAccel = 0.00f;
  mAccelCurrent = SensorManager.GRAVITY_EARTH;
  mAccelLast = SensorManager.GRAVITY_EARTH;
  
  text = (TextView) findViewById(R.id.text);
 }
  
  private final SensorEventListener mSensorListener = new SensorEventListener() {

    public void onSensorChanged(SensorEvent se) {
      float x = se.values[0];
      float y = se.values[1];
      float z = se.values[2];
      mAccelLast = mAccelCurrent;
      mAccelCurrent = (float) Math.sqrt((double) (x*x + y*y + z*z));
      float delta = mAccelCurrent - mAccelLast;
      mAccel = mAccel * 0.9f + delta; // perform low-cut filter
      
      newTime=System.currentTimeMillis();
      if (mAccel>3&&(newTime>(oldTime+1500))){
    	  drinkList.add("This will be a timestamp");
    	  text.setText(String.valueOf(drinkList.size()));
    	  oldTime=System.currentTimeMillis();
      }
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
  };

  @Override
  protected void onResume() {
    super.onResume();
    mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
  }

  @Override
  protected void onPause() {
    mSensorManager.unregisterListener(mSensorListener);
    super.onPause();
  }
  
//  protected void on
  
}