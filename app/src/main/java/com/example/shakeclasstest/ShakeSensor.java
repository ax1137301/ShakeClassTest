package com.example.shakeclasstest;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.util.Random;

public class ShakeSensor extends Service implements SensorEventListener {
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private float mAccel; //除重力外的加速度
    private float mAccelCurrent; //當前加速度，包括重力
    private float mAccleLater; //最後加速度包括重力

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mSensorManager =(SensorManager) getSystemService(Context.SENSOR_SERVICE); //設定mSensorManager取得系統服務
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER); //設定mAccelerometer的Sensor型態
        //註冊mSensorManager
        mSensorManager.registerListener(this,mAccelerometer,SensorManager.SENSOR_DELAY_UI,new Handler());
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];
        mAccleLater = mAccelCurrent; //設定最後加速度等於當前加速度
        mAccelCurrent = (float) Math.sqrt((double) (x*x+y*y+z*z)); //當前加速度計算
        float delta = mAccelCurrent - mAccleLater;
        mAccel = mAccel * 0.9f + delta; //加速度計算

        if(mAccel > 11){
            Random rnd = new Random();
            int color = Color.argb(255,rnd.nextInt(256),rnd.nextInt(256),rnd.nextInt(256));

            MainActivity.square.setBackgroundColor(color);
            MainActivity.text.setTextColor(color);

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
