package com.example.shakeclasstest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static LinearLayout square;
    public static TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        square = findViewById(R.id.lay);
        text = findViewById(R.id.tv);
        Intent intent = new Intent(this,ShakeSensor.class);
        startService(intent);
    }
}
