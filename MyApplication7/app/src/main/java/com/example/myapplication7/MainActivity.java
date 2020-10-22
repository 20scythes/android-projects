
package com.example.myapplication7;

import android.Manifest;
import android.app.Activity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.core.content.ContextCompat;


import android.content.pm.PackageManager;

import android.view.View;



import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


public class MainActivity extends Activity implements SensorEventListener {

    public int ky=0;
    RadioGroup radioGroup, radioGroup1;

    private SensorManager sensorManager;
    private Sensor accelerometer;




    public TextView currentX, maxY, maxZ;


    double distance=0; double u=0;

    public double paceva=1;
    public double gender=1;
    RadioButton male,female,medium,slow,fast;
    EditText plain_text_input;
    public double height =1;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACTIVITY_RECOGNITION) == PackageManager.PERMISSION_DENIED){
            //ask for permission
            requestPermissions(new String[]{Manifest.permission.ACTIVITY_RECOGNITION}, 12);
        }


        initializeViews();



        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);



    }

    public void initializeViews() {
        currentX = (TextView) findViewById(R.id.currentX);
        maxY = (TextView) findViewById(R.id.maxY);
        maxZ = (TextView) findViewById(R.id.maxZ);
        radioGroup= findViewById(R.id.radioGroup);
        radioGroup1= findViewById(R.id.radioGroup1);
        male= findViewById(R.id.male);
        female= findViewById(R.id.female);
        slow=findViewById(R.id.slow);
        medium=findViewById(R.id.medium);
        fast=findViewById(R.id.fast);
        plain_text_input=findViewById(R.id.plain_text_input);



    }
    public void onRadioButtonClicked(View v) {
        boolean checked = ((RadioButton) v).isChecked();


        switch (v.getId()) {
            case R.id.slow:
                if (checked)
                    paceva = 0.595 / 0.690;
                break;
            case R.id.medium:
                if (checked)
                    paceva = 1.0;
                break;
            case R.id.fast:
                if (checked)
                    paceva = 797 / 690;
                break;


        }
    }

    public void onRadioButtonClicked2(View v) {
        boolean checked = ((RadioButton) v).isChecked();


        switch (v.getId()) {
            case R.id.male:
                if (checked)
                    gender = 0.415;
                break;
            case R.id.female:
                if (checked)
                    gender=0.413;
                break;


        }
    }




    @Override
    protected void onResume() {
        super.onResume();
        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR) != null) {
            maxY.setText("SensorFound");
            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);


        } else
            maxY.setText("not found");




    }

    @Override
    protected void onPause() {
        super.onPause();


    }



    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        ++ky;
        String s="";
        s=plain_text_input.getText().toString();
        if(s=="")
        s="1";


        height=Integer.valueOf(plain_text_input.getText().toString())+1;
        currentX.setText(String.valueOf(ky));
        maxZ.setText(Double.toString(ky*gender*height/100*paceva));

    }





}


